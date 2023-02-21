package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Inspection;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 检查报告 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface InspectionService extends IService<Inspection> {
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
     * @param inspection 筛选条件
     * @param current    当前页码
     * @param size       每页大小
     * @return
     */
    Result paginQuery(Inspection inspection, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    Result insert(Inspection inspection, MultipartFile[] files);

    /**
     * 更新数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    Result update(Inspection inspection, MultipartFile[] files);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

    /**
     * 确认完成
     *
     * @param inspection 主键
     * @return 是否成功
     */
    Result finish(Inspection inspection);


}
