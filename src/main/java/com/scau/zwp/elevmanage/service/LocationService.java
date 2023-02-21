package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Location;


/**
 * <p>
 * 场所 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface LocationService extends IService<Location> {

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
     * @param location 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    Result paginQuery(Location location, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    Result insert(Location location);

    /**
     * 更新数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    Result update(Location location);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);


}
