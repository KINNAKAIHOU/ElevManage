package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "用户对象功能接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    public R<User> queryById(@RequestParam(value = "id") Integer id) {
        return userService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public R<List<User>> getAll() {
        List<User> userList = userService.list();
        if (userList != null)
            return R.success(userList);
        else
            return R.error("查询所有失败");
    }


    /**
     * 分页查询
     *
     * @param user    筛选条件
     * @param current 页码
     * @param size    元素
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/pagin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "元素", required = true, paramType = "query", dataType = "Integer"),
    })
    public R<Page<User>> paginQuery(@RequestBody User user, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<User> pageResult = userService.paginQuery(user, current, size);
        return R.success(pageResult);
    }


    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestBody User user) {
        return userService.insert(user);
    }


    /**
     * 注册用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @ApiOperation("注册用户")
    @PostMapping("/register")
    public R<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 登录用户
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @ApiOperation("登录用户")
    @PostMapping("/login")
    public R<String> login(@RequestBody User user) {
        return userService.login(user);
    }


    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestBody User user) {
        return userService.update(user);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    public R<Boolean> deleteById(@RequestParam(value = "id") Integer id) {
        return userService.deleteById(id);
    }

    /**
     * 通过主键封禁用户
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键封禁用户")
    @GetMapping("/ban")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    public R<Boolean> banById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setIsEnabled("0");
        if (userService.update(user).getCode() == 1)
            return R.success(true);
        else
            return R.error("封禁用户失败");
    }

    /**
     * 通过主键开启用户
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键开启用户")
    @GetMapping("/open")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    public R<Boolean> openById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setIsEnabled("1");
        if (userService.update(user).getCode() == 1)
            return R.success(true);
        else
            return R.error("开启用户失败");
    }

}
