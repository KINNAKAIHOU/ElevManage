package com.scau.zwp.elevmanage.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Manufacturer;
import com.scau.zwp.elevmanage.entity.Message;
import com.scau.zwp.elevmanage.mapper.MessageMapper;
import com.scau.zwp.elevmanage.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息 服务实现类
 * </p>
 *
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/24
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        System.out.println(id);
        Message message = getById(id);
        if (message == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询消息所信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询消息信息成功", message);
    }
}


