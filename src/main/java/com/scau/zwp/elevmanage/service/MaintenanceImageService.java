package com.scau.zwp.elevmanage.service;

import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.MaintenanceImage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 维修报告图片 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface MaintenanceImageService extends IService<MaintenanceImage> {
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

}
