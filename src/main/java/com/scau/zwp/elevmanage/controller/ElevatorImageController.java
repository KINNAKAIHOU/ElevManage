package com.scau.zwp.elevmanage.controller;

import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.service.ElevatorImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 电梯图片 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "电梯图片对象功能接口")
@RestController
@RequestMapping("/elevatorImage")
public class ElevatorImageController {

    @Resource
    private ElevatorImageService elevatorImageService;


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "电梯图片ID", required = true, paramType = "query", dataType = "Integer")
    public Result deleteById(@RequestParam(value = "id") Integer id) {
        return elevatorImageService.deleteById(id);
    }

}
