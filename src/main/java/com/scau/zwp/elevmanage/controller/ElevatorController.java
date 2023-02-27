package com.scau.zwp.elevmanage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Accessory;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.service.ElevatorService;
import com.scau.zwp.elevmanage.vo.ElevatorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin.javascript.navig4.LayerArray;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 电梯 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "电梯对象功能接口")
@RestController
@RequestMapping("/elevator")
public class ElevatorController {
    @Resource
    private ElevatorService elevatorService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "电梯ID", required = true, paramType = "query", dataType = "Integer")
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return elevatorService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Elevator> elevatorList = elevatorService.list();
        if (elevatorList != null)
            return new Result(true, StatusCode.OK, "查询电梯全部数据成功", elevatorList);
        else
            return new Result(false, StatusCode.ERROR, "查询电梯全部数据失败");
    }

    /**
     * 分页查询
     *
     * @param elevator 筛选条件
     * @param current  页码
     * @param size     元素
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/pagin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "元素", required = true, paramType = "query", dataType = "Integer"),
    })
    public Result paginQuery(@RequestBody Elevator elevator, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Elevator> pageResult = (Page<Elevator>) elevatorService.paginQuery(elevator, current, size).getData();
        return new Result(true, StatusCode.OK, "查询电梯分页成功", pageResult);
    }


    /**
     * 新增数据
     *
     * @param elevator 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestPart("elevator") Elevator elevator, @RequestPart(name = "files", required = false) MultipartFile[] files) {
        return elevatorService.insert(elevator, files);
    }


    /**
     * 更新数据
     *
     * @param elevator 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public Result edit(@RequestPart("elevator") Elevator elevator, @RequestPart(name = "files", required = false) MultipartFile[] files, @RequestPart("deleteImageIds") Integer[] deleteImageIds) {
        return elevatorService.update(elevator, files, deleteImageIds);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "电梯ID", required = true, paramType = "query", dataType = "Integer")
    public Result deleteById(@RequestParam(value = "id") Integer id) {
        return elevatorService.deleteById(id);
    }


    /**
     * 通过主键删除多个数据
     *
     * @param idsStr 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除多个数据")
    @DeleteMapping("/deleteMore")
    @ApiImplicitParam(name = "idsStr", value = "电梯ID列表", required = true, paramType = "query", dataType = "String")
    public Result deleteMore(@RequestParam(value = "idsStr") String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的电梯数据为空");
        List<Integer> intList = Arrays.stream(idsStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (Integer integer : intList) {
            if (deleteById(integer).getCode() != 2000) {
                return new Result(false, StatusCode.ERROR, "删除电梯失败");
            }
        }
        return new Result(true, StatusCode.OK, "删除电梯成功");
    }

}
