package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Manufacturer;
import com.scau.zwp.elevmanage.entity.Manufacturer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电梯厂家 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface ManufacturerService extends IService<Manufacturer> {
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
     * @param manufacturer 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    Result paginQuery(Manufacturer manufacturer, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param manufacturer 实例对象
     * @return 实例对象
     */
    Result insert(Manufacturer manufacturer);

    /**
     * 更新数据
     *
     * @param manufacturer 实例对象
     * @return 实例对象
     */
    Result update(Manufacturer manufacturer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

}
