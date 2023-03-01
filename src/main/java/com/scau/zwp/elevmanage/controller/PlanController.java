package com.scau.zwp.elevmanage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Plan;
import com.scau.zwp.elevmanage.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 检查计划 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "检查计划对象功能接口")
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Resource
    private PlanService planService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "检查计划ID", required = true, paramType = "query", dataType = "Integer")
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return planService.queryById(id);
    }


    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Plan> planList = planService.list();
        if (planList != null)
            return new Result(true, StatusCode.OK, "查询检查计划全部数据成功", planList);
        else
            return new Result(false, StatusCode.ERROR, "查询检查计划全部数据失败");
    }

    /**
     * 分页查询
     *
     * @param plan    筛选条件
     * @param current 页码
     * @param size    元素
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/pagin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "元素", required = true, paramType = "query", dataType = "Integer"),
    })
    public Result paginQuery(@RequestBody Plan plan, @RequestParam("current") Integer current, @RequestParam("size") Integer size, @RequestParam(value = "nextStartTime", required = false) String nextStartTime,
                             @RequestParam(value = "nextEndTime", required = false) String nextEndTime, @RequestParam(value = "endStartTime", required = false) String endStartTime,
                             @RequestParam(value = "endEndTime", required = false) String endEndTime) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Plan> pageResult = (Page<Plan>) planService.paginQuery(plan, current, size, nextStartTime, nextEndTime, endStartTime, endEndTime).getData();
        return new Result(true, StatusCode.OK, "查询检查计划分页成功", pageResult);
    }


    /**
     * 新增数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody Plan plan) {
        return planService.insert(plan);
    }


    /**
     * 更新数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public Result edit(@RequestBody Plan plan) {
        return planService.update(plan);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "检查计划ID", required = true, paramType = "query", dataType = "Integer")
    public Result deleteById(@RequestParam(value = "id") Integer id) {
        return planService.deleteById(id);
    }

    /**
     * 检查所有计划
     *
     * @return 是否成功
     */
    @ApiOperation("检查所有计划")
    @GetMapping("/checkAllPlan")
    public Result checkAllPlan() {
        return planService.checkAllPlan();
    }


    /**
     * 通过主键删除多个数据
     *
     * @param idsStr 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除多个数据")
    @DeleteMapping("/deleteMore")
    @ApiImplicitParam(name = "idsStr", value = "计划ID列表", required = true, paramType = "query", dataType = "String")
    public Result deleteMore(@RequestParam(value = "idsStr") String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的计划数据为空");
        List<Integer> intList = Arrays.stream(idsStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (Integer integer : intList) {
            if (deleteById(integer).getCode() != 2000) {
                return new Result(false, StatusCode.ERROR, "删除计划失败");
            }
        }
        return new Result(true, StatusCode.OK, "删除计划成功");

    }
}
