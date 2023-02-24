package com.scau.zwp.elevmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scau.zwp.elevmanage.entity.Manufacturer;
import com.scau.zwp.elevmanage.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息 Mapper 接口
 * </p>
 *
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/24
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
