package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Accessory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.zwp.elevmanage.entity.Accessory;

/**
 * <p>
 * 配件 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:50
 */
public interface AccessoryService extends IService<Accessory> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R<Accessory> queryById(Integer id);

    /**
     * 分页查询
     *
     * @param accessory 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    Page<Accessory> paginQuery(Accessory accessory, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param accessory 实例对象
     * @return 实例对象
     */
    R<Boolean> insert(Accessory accessory);

    /**
     * 更新数据
     *
     * @param accessory 实例对象
     * @return 实例对象
     */
    R<Boolean> update(Accessory accessory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R<Boolean> deleteById(Integer id);

}
