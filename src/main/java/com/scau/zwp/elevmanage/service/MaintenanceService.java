package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Maintenance;
import com.scau.zwp.elevmanage.entity.Maintenance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.zwp.elevmanage.entity.MaintenanceItem;
import com.scau.zwp.elevmanage.vo.MaintenanceVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 维修报告 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface MaintenanceService extends IService<Maintenance> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象r
     */
    Result queryById(Integer id);

    /**
     * 分页查询
     *
     * @param maintenance 筛选条件
     * @param current     当前页码
     * @param size        每页大小
     * @param startTime   开启日期
     * @param endTime     结束日期
     * @return
     */
    Result paginQuery(Maintenance maintenance, Integer current, Integer size, String startTime, String endTime);

    /**
     * 新增数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    Result insert(Maintenance maintenance, MultipartFile[] files);

    /**
     * 更新数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    Result update(Maintenance maintenance, List<MaintenanceItem> maintenanceItemList, MultipartFile[] files);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

}
