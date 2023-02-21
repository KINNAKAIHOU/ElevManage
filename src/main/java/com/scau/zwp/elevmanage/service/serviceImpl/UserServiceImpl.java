package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Storage;
import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.mapper.UserMapper;
import com.scau.zwp.elevmanage.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.utils.JWTUtils;
import jdk.nashorn.internal.parser.Token;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private StringEncryptor stringEncryptor;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        System.out.println(id);
        User user = getById(id);
        if (user == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询用户信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询用户信息成功", user);
    }

    /**
     * 分页查询
     *
     * @param user    筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    public Result paginQuery(User user, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(user.getUserNumber())) {
            queryWrapper.like(User::getUserNumber, user.getUserNumber());
        }
        if (StrUtil.isNotBlank(user.getUserName())) {
            queryWrapper.like(User::getUserName, user.getUserName());
        }
        if (StrUtil.isNotBlank(user.getPassword())) {
            queryWrapper.like(User::getPassword, user.getPassword());
        }
        if (StrUtil.isNotBlank(user.getGender())) {
            queryWrapper.like(User::getGender, user.getGender());
        }
        if (StrUtil.isNotBlank(user.getAge())) {
            queryWrapper.like(User::getAge, user.getAge());
        }
        if (StrUtil.isNotBlank(user.getPermissionName())) {
            queryWrapper.like(User::getPermissionName, user.getPermissionName());
        }
        if (StrUtil.isNotBlank(user.getIsEnabled())) {
            queryWrapper.like(User::getIsEnabled, user.getIsEnabled());
        }
        //2. 执行分页查询
        Page<User> pagin = new Page<>(current, size, true);
        IPage<User> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询用户分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     * 等待修改
     */
    public Result insert(User user) {
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = "US" + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("user_number", number);
            if (getOne(queryWrapper) != null)
                continue;
            else
                break;
        }
        user.setUserNumber(number);
        user.setIsEnabled("1");
        if (save(user) == true)
            return new Result(true, StatusCode.OK, "添加用户成功");
        else
            return new Result(false, StatusCode.ERROR, "添加用户失败");

    }

    /**
     * 注册用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Result register(User user) {
        user.setPassword(stringEncryptor.encrypt(user.getPassword()));
        if (insert(user).getCode() == 2000)
            return new Result(true, StatusCode.OK, "添加用户成功", user);
        else
            return new Result(false, StatusCode.ERROR, "添加用户失败");
    }


    /**
     * 登录用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Result login(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_number", user.getUserNumber());
        User newUser = getOne(queryWrapper);
        if (newUser == null)
            return new Result(false, StatusCode.ERROR, "用户编号不存在");
        System.out.println(stringEncryptor.decrypt(newUser.getPassword()));
        if (stringEncryptor.decrypt(newUser.getPassword()).equals(user.getPassword())) {
            Map<String, User> payload = new HashMap<>();
            payload.put("user", user);
            String token = "";
            token = JWTUtils.getToken(payload);
            List<Object> list = new ArrayList<>();
            list.add(token);
            list.add(newUser);
            return new Result(true, StatusCode.OK, "登录成功", list);
        }
        return new Result(false, StatusCode.ERROR, "密码错误");
    }


    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Result update(User user) {
        if (updateById(user) == true) {
            return new Result(true, StatusCode.OK, "更新用户数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新用户数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        if (removeById(id) == true)
            return new Result(true, StatusCode.OK, "删除用户成功");
        else
            return new Result(false, StatusCode.ERROR, "删除用户失败");
    }

}
