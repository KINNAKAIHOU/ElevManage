package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电梯 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
public interface ElevatorService extends IService<Elevator> {

    /**
     * 分页查询
     *
     * @param elevator 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    Page<Elevator> paginQuery(Elevator elevator, long current, long size);

}
