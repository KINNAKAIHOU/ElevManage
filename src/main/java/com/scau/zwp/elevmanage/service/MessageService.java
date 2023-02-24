package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Message;

/**
 * <p>
 * 消息 服务类
 * </p>
 *
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/24
 */
public interface MessageService extends IService<Message> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);
}
