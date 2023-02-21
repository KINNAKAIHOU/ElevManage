package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
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
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return userService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<User> userList = userService.list();
        if (userList != null)
            return new Result(true, StatusCode.OK, "查询用户全部数据成功", userList);
        else
            return new Result(false, StatusCode.ERROR, "查询用户全部数据失败");
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
    public Result paginQuery(@RequestBody User user, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<User> pageResult = (Page<User>) userService.paginQuery(user, current, size).getData();
        return new Result(true, StatusCode.OK, "查询用户分页成功", pageResult);
    }


    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody User user) {
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
    public Result register(@RequestBody User user) {
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
    public Result login(@RequestBody User user) {
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
    public Result edit(@RequestBody User user) {
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
    public Result deleteById(@RequestParam(value = "id") Integer id) {
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
    public Result banById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setIsEnabled("0");
        if (userService.update(user).getCode() == 1)
            return new Result(true, StatusCode.OK, "封禁用户成功");
        else
            return new Result(false, StatusCode.ERROR, "封禁用户失败");
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
    public Result openById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setIsEnabled("1");
        if (userService.update(user).getCode() == 1)
            return new Result(true, StatusCode.OK, "开启用户成功");
        else
            return new Result(false, StatusCode.ERROR, "开启用户失败");
    }

}
