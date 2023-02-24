package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.zwp.elevmanage.common.IndexData;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.service.ElevatorService;
import com.scau.zwp.elevmanage.service.InspectionService;
import com.scau.zwp.elevmanage.service.MaintenanceService;
import com.scau.zwp.elevmanage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/21
 */

@RestController
@Api(tags = "主页对象功能接口")
@RequestMapping("/index")
public class IndexController {

    @Resource
    private ElevatorService elevatorService;
    @Resource
    private InspectionService inspectionService;
    @Resource
    private MaintenanceService maintenanceService;

    /**
     * 获取主页数据数据
     *
     * @return 是否成功
     */
    @ApiOperation("获取主页数据数据")
    @GetMapping
    public Result index() {
        IndexData indexData = new IndexData();
        indexData.setElevatorCount((int) elevatorService.count());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_finish", "0");
        indexData.setNotFinishInspection((int) inspectionService.count(queryWrapper));
        indexData.setNotFinishMaintenance((int) maintenanceService.count(queryWrapper));
        return new Result(true, StatusCode.OK, "查询主页数据成功", indexData);
    }

}
