package com.scau.zwp.elevmanage.service.serviceImpl;

import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.InspectionImage;
import com.scau.zwp.elevmanage.mapper.InspectionImageMapper;
import com.scau.zwp.elevmanage.service.InspectionImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 检查报告图片 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class InspectionImageServiceImpl extends ServiceImpl<InspectionImageMapper, InspectionImage> implements InspectionImageService {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public R<Boolean> deleteById(Integer id) {
        if (removeById(id) == true)
            return R.success(true);
        else
            return R.error("通过主键删除数据失败");
    }

}
