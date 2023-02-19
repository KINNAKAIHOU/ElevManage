package com.scau.zwp.elevmanage.service;

import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.ElevatorImage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电梯图片 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface ElevatorImageService extends IService<ElevatorImage> {


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R<Boolean> deleteById(Integer id);

}
