package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;

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
    R<Inventory> queryById(Integer id);


    /**
     * 分页查询
     *
     * @param inventoryVo 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    Page<Inventory> paginQuery(Inventory inventoryVo, Integer current, Integer size);


    /**
     * 更新数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    R<Boolean> update(Inventory inventory);

    /**
     * 增加数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */

    R<Boolean> increase(Integer accessoryId, Integer size);


    /**
     * 减少数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    R<Boolean> reduce(Integer accessoryId, Integer size);


}
