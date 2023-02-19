package com.scau.zwp.elevmanage.service.serviceImpl;

import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.mapper.UserMapper;
import com.scau.zwp.elevmanage.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
