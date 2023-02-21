package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public R<User> queryById(Integer id) {
        System.out.println(id);
        User user = getById(id);
        if (user == null)
            return R.error("通过ID查询电梯厂家信息失败");
        else
            return R.success(user);
    }

    /**
     * 分页查询
     *
     * @param user    筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    public Page<User> paginQuery(User user, Integer current, Integer size) {
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
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     * 等待修改
     */
    public R<Boolean> insert(User user) {
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
            return R.success(true);
        else
            return R.error("新增数据失败");
    }

    /**
     * 注册用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public R<User> register(User user) {
        user.setPassword(stringEncryptor.encrypt(user.getPassword()));
        insert(user);
        return R.success(user);
    }


    /**
     * 登录用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public R<String> login(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_number", user.getUserNumber());
        User newUser = getOne(queryWrapper);
        if (newUser == null)
            return R.error("sb");
        System.out.println(stringEncryptor.decrypt(newUser.getPassword()));
        if (stringEncryptor.decrypt(newUser.getPassword()).equals(user.getPassword())) {
            Map<String, User> payload = new HashMap<>();
            payload.put("user", user);
            String token = "";
            token = JWTUtils.getToken(payload);
            return R.success(token);
        }
        return R.error("登录失败");
    }


    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(User user) {
        if (updateById(user) == true) {
            return R.success(true);
        } else
            return R.error("更新数据失败");
    }

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
