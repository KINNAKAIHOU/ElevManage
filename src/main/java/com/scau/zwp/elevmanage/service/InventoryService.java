package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * <p>
 * 库存管理 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface InventoryService extends IService<Inventory> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);


    /**
     * 分页查询
     *
     * @param inventoryVo 筛选条件
     * @param current     当前页码
     * @param size        每页大小
     * @return
     */
    Result paginQuery(Inventory inventoryVo, Integer current, Integer size);


    /**
     * 更新数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    Result update(Inventory inventory);

    /**
     * 增加数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */

    Result increase(Integer accessoryId, Integer size);


    /**
     * 减少数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    Result reduce(Integer accessoryId, Integer size);


    /**
     * 检查所有库存
     *
     * @return 实例对象
     */
    @ApiOperation("检查所有库存")
    Result checkAllInventory();


}
