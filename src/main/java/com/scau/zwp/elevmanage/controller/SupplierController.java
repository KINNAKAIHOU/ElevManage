package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Storage;
import com.scau.zwp.elevmanage.entity.Supplier;
import com.scau.zwp.elevmanage.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 供货商 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "供货商对象功能接口")
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierService supplierService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "供货商ID", required = true, paramType = "query", dataType = "Integer")
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return supplierService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Supplier> storageList = supplierService.list();
        if (storageList != null)
            return new Result(true, StatusCode.OK, "查询供货商全部数据成功", storageList);
        else
            return new Result(false, StatusCode.ERROR, "查询供货商全部数据失败");
    }


    /**
     * 分页查询
     *
     * @param supplier 筛选条件
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
    public Result paginQuery(@RequestBody Supplier supplier, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Supplier> pageResult = (Page<Supplier>) supplierService.paginQuery(supplier, current, size).getData();
        return new Result(true, StatusCode.OK, "查询供货商分页成功", pageResult);
    }


    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        return supplierService.insert(supplier);
    }


    /**
     * 更新数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public Result edit(@RequestBody Supplier supplier) {
        return supplierService.update(supplier);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "供货商ID", required = true, paramType = "query", dataType = "Integer")
    public Result deleteById(@RequestParam(value = "id") Integer id) {
        return supplierService.deleteById(id);
    }


}
