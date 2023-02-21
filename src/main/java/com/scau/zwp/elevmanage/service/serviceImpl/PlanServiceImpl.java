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
import com.scau.zwp.elevmanage.service.PlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
    public Result paginQuery(Plan plan, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Plan> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(plan.getPlanNumber())) {
            queryWrapper.like(Plan::getPlanNumber, plan.getPlanNumber());
        }
        if (StrUtil.isNotBlank(plan.getIsFinish())) {
            queryWrapper.like(Plan::getIsFinish, plan.getIsFinish());
        }
        if (StrUtil.isNotBlank(plan.getElevatorNumber())) {
            queryWrapper.like(Plan::getElevatorNumber, plan.getElevatorNumber());
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
        if (save(plan) == true)
            return new Result(true, StatusCode.OK, "添加检查计划成功");
        else
            return new Result(false, StatusCode.ERROR, "添加检查计划失败");

    }

    /**
     * 更新数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    public Result update(Plan plan) {
        if (updateById(plan) == true) {
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
        if (removeById(id) == true) {
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
        if (planList != null) {
            for (Plan plan : planList) {
                LocalDateTime nextTime = plan.getNextTime();
                if (nextTime == null)
                    nextTime = plan.getStartTime();
                LocalDateTime nowTime = LocalDateTime.now();
                nextTime.withHour(0).withMinute(0).withSecond(0);
                System.out.println(nextTime);
                if (nowTime.isAfter(nextTime)) {
                    int num = plan.getIntervalUnit();
                    int size = plan.getIntervalTime();
                    if (num == 1) {
                        nextTime = nextTime.plusDays(size);
                    } else if (num == 2) {
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
                        Inspection inspection = new Inspection();
                        Elevator elevator = elevatorMapper.selectById(plan.getElevatorId());
                        inspection.setElevatorNumber(elevator.getElevatorNumber());
                        inspection.setLocationName(elevator.getLocationName());
                        inspection.setAddress(elevator.getAddress());
                        inspection.setContactPerson(elevator.getContactPerson());
                        inspection.setContactNumber(elevator.getContactNumber());
                        inspection.setElevatorId(elevator.getId());
                        if (inspectionService.insert(inspection, null).getCode() == 0)
                            return new Result(false, StatusCode.ERROR, "新增检查报告失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "更新检查计划数据失败");
                }
            }
        }
        return new Result(true, StatusCode.OK, "检查计划遍历成功");
    }
}
