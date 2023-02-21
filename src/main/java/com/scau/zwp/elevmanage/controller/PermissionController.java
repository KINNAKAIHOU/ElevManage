package com.scau.zwp.elevmanage.controller;

import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Permission;
import com.scau.zwp.elevmanage.entity.User;
import com.scau.zwp.elevmanage.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "权限对象功能接口")
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public R<List<Permission>> getAll() {
        List<Permission> permissionList = permissionService.list();
        if (permissionList != null)
            return R.success(permissionList);
        else
            return R.error("查询所有失败");
    }

}
