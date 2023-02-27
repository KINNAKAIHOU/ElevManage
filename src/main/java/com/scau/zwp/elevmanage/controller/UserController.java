package com.scau.zwp.elevmanage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.service.UserService;
import com.scau.zwp.elevmanage.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    @GetMapping("/me")
    @ApiOperation("获取已登录用户信息")
    public Result getUserLoginInfo(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            System.out.println("cookies is null");
            return new Result(false, StatusCode.ERROR, "获取已登录用户信息失败");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("elevManage-token"))
                    token = cookie.getValue();
            }
        }
        if (token == null) {
            System.out.println("token is null");
            return new Result(false, StatusCode.ERROR, "获取已登录用户信息失败");
        }
        DecodedJWT verify = JWTUtils.verify(token);
        return userService.getUserLoginInfo(verify);
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
    public Result paginQuery(@RequestBody(required = false) User user, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
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
     * 修改密码
     *
     * @param id          用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 实例对象
     */
    @ApiOperation("修改密码")
    @PutMapping("/renewPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, paramType = "query", dataType = "Integer"),
    })
    public Result renewPassword(@RequestParam(value = "id") Integer id, @RequestParam(value = "oldPassword", required = false) String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        return userService.renewPassword(id, oldPassword, newPassword);
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
     * 通过主键删除多个数据
     *
     * @param idsStr 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除多个数据")
    @DeleteMapping("/deleteMore")
    @ApiImplicitParam(name = "idsStr", value = "用户ID列表", required = true, paramType = "query", dataType = "String")
    public Result deleteMore(@RequestParam(value = "idsStr") String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");
        List<Integer> intList = Arrays.stream(idsStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (Integer integer : intList) {
            if (deleteById(integer).getCode() != 2000) {
                return new Result(false, StatusCode.ERROR, "删除用户失败");
            }
        }
        return new Result(true, StatusCode.OK, "删除用户成功");
    }


    /**
     * 通过主键封禁用户
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键封禁用户")
    @PutMapping("/ban")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    public Result banById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setIsEnabled("0");
        if (userService.updateById(user))
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
    @PutMapping("/open")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Integer")
    public Result openById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setIsEnabled("1");
        if (userService.updateById(user))
            return new Result(true, StatusCode.OK, "开启用户成功");
        else
            return new Result(false, StatusCode.ERROR, "开启用户失败");
    }


}
