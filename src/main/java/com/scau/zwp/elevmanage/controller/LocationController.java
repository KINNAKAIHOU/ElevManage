package com.scau.zwp.elevmanage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 场所 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "场所对象功能接口")
@RestController
@RequestMapping("/location")
public class LocationController {
    @Resource
    private LocationService locationService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "场所ID", required = true, paramType = "query", dataType = "Integer")
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return locationService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Location> locationList = locationService.list();
        if (locationList != null)
            return new Result(true, StatusCode.OK, "查询场所全部数据成功", locationList);
        else
            return new Result(false, StatusCode.ERROR, "查询场所全部数据失败");
    }


    /**
     * 分页查询
     *
     * @param location 筛选条件
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
    public Result paginQuery(@RequestBody Location location, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Location> pageResult = (Page<Location>) locationService.paginQuery(location, current, size).getData();
        return new Result(true, StatusCode.OK, "查询场所分页成功", pageResult);
    }


    /**
     * 新增数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody Location location) {
        return locationService.insert(location);
    }


    /**
     * 更新数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public Result edit(@RequestBody Location location) {
        return locationService.update(location);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "场所ID", required = true, paramType = "query", dataType = "Integer")
    public Result deleteById(@RequestParam(value = "id") Integer id) {
        return locationService.deleteById(id);
    }


    /**
     * 通过主键删除多个数据
     *
     * @param idsStr 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除多个数据")
    @DeleteMapping("/deleteMore")
    @ApiImplicitParam(name = "idsStr", value = "场所ID列表", required = true, paramType = "query", dataType = "String")
    public Result deleteMore(@RequestParam(value = "idsStr") String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的场所数据为空");
        List<Integer> intList = Arrays.stream(idsStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (Integer integer : intList) {
            if (deleteById(integer).getCode() != 2000) {
                return new Result(false, StatusCode.ERROR, "删除场所失败");
            }
        }
        return new Result(true, StatusCode.OK, "删除场所成功");
    }

}
