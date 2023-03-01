package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.*;
import com.scau.zwp.elevmanage.entity.Plan;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.mapper.PlanMapper;
import com.scau.zwp.elevmanage.service.InspectionService;
import com.scau.zwp.elevmanage.service.MessageService;
import com.scau.zwp.elevmanage.service.PlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 检查计划 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
    @Resource
    private ElevatorMapper elevatorMapper;
    @Resource
    private InspectionService inspectionService;
    @Resource
    private MessageService messageService;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        System.out.println(id);
        Plan plan = getById(id);
        if (plan == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询检查计划信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询检查计划信息成功", plan);
    }

    /**
     * 分页查询
     *
     * @param plan    筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    public Result paginQuery(Plan plan, Integer current, Integer size, String nextStartTime, String nextEndTime, String endStartTime, String endEndTime) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Plan> queryWrapper = new LambdaQueryWrapper<>();
        if (plan.getElevatorId() != null) {
            queryWrapper.eq(Plan::getElevatorId, plan.getElevatorId());
        }
        if (StrUtil.isNotBlank(plan.getIsFinish())) {
            queryWrapper.eq(Plan::getIsFinish, plan.getIsFinish());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (nextStartTime != null && nextStartTime != "") {
            LocalDateTime startDateTime = LocalDateTime.parse(nextStartTime, formatter);
            queryWrapper.ge(Plan::getNextTime, startDateTime);
        }
        if (nextEndTime != null && nextEndTime != "") {
            LocalDateTime endDateTime = LocalDateTime.parse(nextEndTime, formatter);
            queryWrapper.le(Plan::getNextTime, endDateTime);
        }
        if (endStartTime != null && endStartTime != "") {
            LocalDateTime startDateTime = LocalDateTime.parse(endStartTime, formatter);
            queryWrapper.ge(Plan::getEndTime, startDateTime);
        }
        if (endEndTime != null && endEndTime != "") {
            LocalDateTime endDateTime = LocalDateTime.parse(endEndTime, formatter);
            queryWrapper.le(Plan::getEndTime, endDateTime);
        }
        //2. 执行分页查询
        Page<Plan> pagin = new Page<>(current, size, true);
        IPage<Plan> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询检查计划分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    public Result insert(Plan plan) {
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = "PL" + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<Plan> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("plan_number", number);
            if (getOne(queryWrapper) != null)
                continue;
            else
                break;
        }
        plan.setPlanNumber(number);
        Elevator elevator = elevatorMapper.selectById(plan.getElevatorId());
        plan.setElevatorNumber(elevator.getElevatorNumber());
        plan.setElevatorName(elevator.getElevatorName());
        LocalDateTime startTime = plan.getStartTime();
        LocalDateTime nextTime = null;
        String unit = plan.getIntervalUnit();
        int size = plan.getIntervalTime();
        if (unit.equals("天")) {
            nextTime = startTime.plusDays(size);
        } else if (unit.equals("周")) {
            nextTime = startTime.plusWeeks(size);
        } else if (unit.equals("月")) {
            nextTime = startTime.plusMonths(size);
        } else {
            nextTime = startTime.plusYears(size);
        }
        if (plan.getEndTime().isBefore(nextTime))
            return new Result(false, StatusCode.ERROR, "添加检查计划失败,结束日期早于下一次生成检查日期");
        plan.setNextTime(nextTime);
        if (save(plan) == true) {
            Message message = new Message();
            message.setMessage("添加新检查计划  " + plan.getPlanNumber() + "--" + plan.getElevatorNumber() + ":" + plan.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "添加检查计划成功");
        } else
            return new Result(false, StatusCode.ERROR, "添加检查计划失败");

    }

    /**
     * 更新数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    public Result update(Plan plan) {
        Elevator elevator = elevatorMapper.selectById(plan.getElevatorId());
        plan.setElevatorNumber(elevator.getElevatorNumber());
        plan.setElevatorName(elevator.getElevatorName());
        LocalDateTime startTime = plan.getStartTime();
        LocalDateTime nextTime = null;
        String unit = plan.getIntervalUnit();
        int size = plan.getIntervalTime();
        if (unit == "天") {
            nextTime = startTime.plusDays(size);
        } else if (unit == "周") {
            nextTime = startTime.plusWeeks(size);
        } else if (unit == "月") {
            nextTime = startTime.plusMonths(size);
        } else {
            nextTime = startTime.plusYears(size);
        }
        if (plan.getEndTime().isBefore(nextTime))
            return new Result(false, StatusCode.ERROR, "更新检查计划数据失败,结束日期早于下一次生成检查日期");
        plan.setNextTime(nextTime);
        if (updateById(plan) == true) {
            Message message = new Message();
            message.setMessage("更新检查计划内容  " + plan.getPlanNumber() + "--" + plan.getElevatorNumber() + ":" + plan.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "更新检查计划数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新检查计划数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        Plan plan = getById(id);
        if (removeById(id) == true) {
            Message message = new Message();
            message.setMessage("删除检查计划  " + plan.getPlanNumber() + "--" + plan.getElevatorNumber() + ":" + plan.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "删除检查计划成功");
        } else
            return new Result(false, StatusCode.ERROR, "删除检查计划失败");
    }

    /**
     * 检查所有计划
     *
     * @return 是否成功
     */
    @ApiOperation("检查所有计划")
    @DeleteMapping
    public Result checkAllPlan() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_finish", 0);
        List<Plan> planList = list(queryWrapper);
        List<Plan> renewPlanList = new ArrayList<>();
        if (planList != null) {
            for (Plan plan : planList) {
                LocalDateTime nextTime = plan.getNextTime();
                LocalDateTime nowTime = LocalDateTime.now();
                nextTime.withHour(0).withMinute(0).withSecond(0);
                //System.out.println(nextTime);
                if (nowTime.isAfter(nextTime)) {
                    String unit = plan.getIntervalUnit();
                    int size = plan.getIntervalTime();
                    if (unit.equals("天")) {
                        nextTime = nextTime.plusDays(size);
                    } else if (unit.equals("周")) {
                        nextTime = nextTime.plusWeeks(size);
                    } else if (unit.equals("月")) {
                        nextTime = nextTime.plusMonths(size);
                    } else {
                        nextTime = nextTime.plusYears(size);
                    }
                    LocalDateTime endTime = plan.getEndTime();
                    endTime.withHour(0).withMinute(0).withSecond(0);
                    if (endTime.isBefore(nextTime) || endTime.equals(nowTime))
                        plan.setIsFinish("1");
                    plan.setNextTime(nextTime);
                    if (updateById(plan)) {
                        renewPlanList.add(plan);
                        Inspection inspection = new Inspection();
                        inspection.setElevatorId(plan.getElevatorId());
                        if (inspectionService.insert(inspection, null).getCode() == 2001)
                            return new Result(false, StatusCode.ERROR, "新增检查报告失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "更新检查计划数据失败");
                }
            }
        }
        return new Result(true, StatusCode.OK, "检查计划遍历成功", renewPlanList);
    }
}