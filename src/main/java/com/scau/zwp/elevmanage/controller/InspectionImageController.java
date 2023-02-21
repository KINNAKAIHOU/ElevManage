package com.scau.zwp.elevmanage.controller;

import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.service.ElevatorImageService;
import com.scau.zwp.elevmanage.service.InspectionImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 检查报告图片 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "检查报告图片对象功能接口")
@RestController
@RequestMapping("/inspectionImage")
public class InspectionImageController {

    @Resource
    private InspectionImageService inspectionImageService;


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "维修报告图片ID", required = true, paramType = "query", dataType = "Integer")
    public R<Boolean> deleteById(@RequestParam(value = "id") Integer id) {
        return inspectionImageService.deleteById(id);
    }

}
