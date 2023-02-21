package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface UserService extends IService<User> {
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
     * @param user    筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    Result paginQuery(User user, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result insert(User user);

    /**
     * 注册用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result register(User user);

    /**
     * 登录用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result login(User user);


    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

}
