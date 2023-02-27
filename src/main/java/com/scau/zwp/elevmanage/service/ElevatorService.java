package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.zwp.elevmanage.vo.ElevatorVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 电梯 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface ElevatorService extends IService<Elevator> {
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
     * @param elevator 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    Result paginQuery(Elevator elevator, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param elevator 实例对象
     * @return 实例对象
     */
    Result insert(Elevator elevator, MultipartFile[] files);

    /**
     * 更新数据
     *
     * @param elevator 实例对象
     * @return 实例对象
     */
    Result update(Elevator elevator, MultipartFile[] files,Integer[] deleteImageIds);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

}
