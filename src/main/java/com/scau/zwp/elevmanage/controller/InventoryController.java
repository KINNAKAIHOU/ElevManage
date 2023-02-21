package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Inventory;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 库存管理 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "库存管理对象功能接口")
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Resource
    private InventoryService inventoryService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "配件ID", required = true, paramType = "query", dataType = "Integer")
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return inventoryService.queryById(id);
    }


    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Inventory> inventoryList = inventoryService.list();
        if (inventoryList != null)
            return new Result(true, StatusCode.OK, "查询库存管理全部数据成功", inventoryList);
        else
            return new Result(false, StatusCode.ERROR, "查询库存管理全部数据失败");
    }

    /**
     * 分页查询
     *
     * @param inventoryVo 筛选条件
     * @param current     页码
     * @param size        元素
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/pagin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "元素", required = true, paramType = "query", dataType = "Integer"),
    })
    public Result paginQuery(@RequestBody Inventory inventoryVo, @RequestParam("current") Integer
            current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Inventory> pageResult = (Page<Inventory>) inventoryService.paginQuery(inventoryVo, current, size).getData();
        return new Result(true, StatusCode.OK, "查询库存管理分页成功", pageResult);
    }


    /**
     * 更新数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public Result edit(@RequestBody Inventory inventory) {
        return inventoryService.update(inventory);
    }

    /**
     * 增加数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    @ApiOperation("增加数量")
    @PutMapping("/increase")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessoryId", value = "配件ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", required = true, paramType = "query", dataType = "Integer"),
    })
    public Result increase(@RequestParam("accessoryId") Integer accessoryId, @RequestParam("size") Integer size) {
        return inventoryService.increase(accessoryId, size);
    }

    /**
     * 减少数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    @ApiOperation("减少数量")
    @PutMapping("/reduce")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessoryId", value = "配件ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "数量", required = true, paramType = "query", dataType = "Integer"),
    })
    public Result reduce(@RequestParam("accessoryId") Integer accessoryId, @RequestParam("size") Integer size) {
        return inventoryService.reduce(accessoryId, size);
    }


    /**
     * 检查所有库存
     *
     * @return 实例对象
     */
    @ApiOperation("检查所有库存")
    @GetMapping("/checkAllInventory")
    public Result checkAllInventory() {
        return inventoryService.checkAllInventory();
    }


}
