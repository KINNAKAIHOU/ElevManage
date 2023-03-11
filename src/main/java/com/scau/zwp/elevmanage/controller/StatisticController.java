package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.*;
import com.scau.zwp.elevmanage.service.ElevatorService;
import com.scau.zwp.elevmanage.service.MaintenanceItemService;
import com.scau.zwp.elevmanage.service.MaintenanceService;
import com.scau.zwp.elevmanage.service.StorageItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/3/7
 */
@Api(tags = "数据统计功能接口")
@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Resource
    private MaintenanceService maintenanceService;
    @Resource
    private ElevatorService elevatorService;
    @Resource
    private StorageItemService storageItemService;
    @Resource
    private MaintenanceItemService maintenanceItemService;

    /**
     * 查询维修工人数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询维修工人数据")
    @GetMapping("/getRepairUserStatistic")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true, paramType = "query", dataType = "String")
    public Result getRepairUserStatistic(@RequestParam(value = "name") String name) {

        List<Integer> list = new ArrayList<>();
        LambdaQueryWrapper<Maintenance> queryWrapper = new LambdaQueryWrapper<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).plusMonths(1);
        LocalDateTime localDateTime1 = localDateTime.plusMonths(-1);
        LocalDateTime localDateTime2 = localDateTime.plusMonths(-2);
        LocalDateTime localDateTime3 = localDateTime.plusMonths(-3);
        LocalDateTime localDateTime4 = localDateTime.plusMonths(-4);

        queryWrapper.ge(Maintenance::getMaintenanceData, localDateTime4);
        queryWrapper.le(Maintenance::getMaintenanceData, localDateTime3);
        if (!name.equals("") && !name.equals("undefined"))
            queryWrapper.eq(Maintenance::getMaintenancePerson, name);
        list.add((int) maintenanceService.count(queryWrapper));

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Maintenance::getMaintenanceData, localDateTime3);
        queryWrapper.le(Maintenance::getMaintenanceData, localDateTime2);
        if (!name.equals("") && !name.equals("undefined"))
            queryWrapper.eq(Maintenance::getMaintenancePerson, name);
        list.add((int) maintenanceService.count(queryWrapper));

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Maintenance::getMaintenanceData, localDateTime2);
        queryWrapper.le(Maintenance::getMaintenanceData, localDateTime1);
        if (!name.equals("") && !name.equals("undefined"))
            queryWrapper.eq(Maintenance::getMaintenancePerson, name);
        list.add((int) maintenanceService.count(queryWrapper));

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Maintenance::getMaintenanceData, localDateTime1);
        queryWrapper.le(Maintenance::getMaintenanceData, localDateTime);
        if (!name.equals("") && !name.equals("undefined"))
            queryWrapper.eq(Maintenance::getMaintenancePerson, name);
        list.add((int) maintenanceService.count(queryWrapper));

        return new Result(true, StatusCode.OK, "查询维修工人数据成功", list);
    }

    /**
     * 查询电梯厂家数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询电梯厂家数据")
    @GetMapping("/getManufacturerStatistic")
    @ApiImplicitParam(name = "id", value = "电梯厂家id", required = true, paramType = "query", dataType = "String")
    public Result getManufacturerStatistic(@RequestParam(value = "id") String id) {

        List<Integer> list = new ArrayList<>();
        List<Elevator> elevatorList = new ArrayList<>();
        List<Maintenance> maintenanceList = new ArrayList<>();
        if (!id.equals("") && !id.equals("undefined")) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("manufacturer_id", Integer.parseInt(id));
            elevatorList = elevatorService.list(queryWrapper1);
        }
        LambdaQueryWrapper<Maintenance> queryWrapper = new LambdaQueryWrapper<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).plusMonths(1);
        LocalDateTime localDateTime1 = localDateTime.plusMonths(-1);
        LocalDateTime localDateTime2 = localDateTime.plusMonths(-2);
        LocalDateTime localDateTime3 = localDateTime.plusMonths(-3);
        LocalDateTime localDateTime4 = localDateTime.plusMonths(-4);

        queryWrapper.ge(Maintenance::getCreateTime, localDateTime4);
        queryWrapper.le(Maintenance::getCreateTime, localDateTime3);
        maintenanceList = maintenanceService.list(queryWrapper);
        if (!id.equals("") && !id.equals("undefined")) {
            int num = 0;
            for (Maintenance maintenance : maintenanceList) {
                for (Elevator elevator : elevatorList) {
                    if (maintenance.getElevatorName().equals(elevator.getElevatorName())) {
                        ++num;
                        continue;
                    }
                }
            }
            list.add(num);
        } else
            list.add(maintenanceList.size());

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Maintenance::getCreateTime, localDateTime3);
        queryWrapper.le(Maintenance::getCreateTime, localDateTime2);
        maintenanceList = maintenanceService.list(queryWrapper);
        if (!id.equals("") && !id.equals("undefined")) {
            int num = 0;
            for (Maintenance maintenance : maintenanceList) {
                for (Elevator elevator : elevatorList) {
                    if (maintenance.getElevatorName().equals(elevator.getElevatorName())) {
                        ++num;
                        continue;
                    }
                }
            }
            list.add(num);
        } else
            list.add(maintenanceList.size());

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Maintenance::getCreateTime, localDateTime2);
        queryWrapper.le(Maintenance::getCreateTime, localDateTime1);
        maintenanceList = maintenanceService.list(queryWrapper);
        if (!id.equals("") && !id.equals("undefined")) {
            int num = 0;
            for (Maintenance maintenance : maintenanceList) {
                for (Elevator elevator : elevatorList) {
                    if (maintenance.getElevatorName().equals(elevator.getElevatorName())) {
                        ++num;
                        continue;
                    }
                }
            }
            list.add(num);
        } else
            list.add(maintenanceList.size());

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Maintenance::getCreateTime, localDateTime1);
        queryWrapper.le(Maintenance::getCreateTime, localDateTime);
        maintenanceList = maintenanceService.list(queryWrapper);
        if (!id.equals("") && !id.equals("undefined")) {
            int num = 0;
            for (Maintenance maintenance : maintenanceList) {
                for (Elevator elevator : elevatorList) {
                    if (maintenance.getElevatorName().equals(elevator.getElevatorName())) {
                        ++num;
                        continue;
                    }
                }
            }
            list.add(num);
        } else
            list.add(maintenanceList.size());

        return new Result(true, StatusCode.OK, "查询电梯厂家数据成功", list);
    }

    /**
     * 查询入库配件数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询入库配件数据")
    @GetMapping("/getAccessoryStorageStatistic")
    @ApiImplicitParam(name = "id", value = "配件ID", required = true, paramType = "query", dataType = "String")
    public Result getAccessoryStorageStatistic(@RequestParam(value = "id") String id) {

        List<Integer> list = new ArrayList<>();
        List<BigDecimal> bigDecimalList = new ArrayList<>();
        List<StorageItem> storageItemList = new ArrayList<>();
        LambdaQueryWrapper<StorageItem> queryWrapper = new LambdaQueryWrapper<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).plusMonths(1);
        LocalDateTime localDateTime1 = localDateTime.plusMonths(-1);
        LocalDateTime localDateTime2 = localDateTime.plusMonths(-2);
        LocalDateTime localDateTime3 = localDateTime.plusMonths(-3);
        LocalDateTime localDateTime4 = localDateTime.plusMonths(-4);

        queryWrapper.ge(StorageItem::getCreateTime, localDateTime4);
        queryWrapper.le(StorageItem::getCreateTime, localDateTime3);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(StorageItem::getAccessoryId, Integer.parseInt(id));
        storageItemList = storageItemService.list(queryWrapper);
        list.add(storageItemList.size());
        BigDecimal price = new BigDecimal(0.0);
        for (StorageItem storageItem : storageItemList) {
            price = price.add(storageItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(StorageItem::getCreateTime, localDateTime3);
        queryWrapper.le(StorageItem::getCreateTime, localDateTime2);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(StorageItem::getAccessoryId, Integer.parseInt(id));
        storageItemList = storageItemService.list(queryWrapper);
        list.add(storageItemList.size());
        price = new BigDecimal(0.0);
        for (StorageItem storageItem : storageItemList) {
            price = price.add(storageItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(StorageItem::getCreateTime, localDateTime2);
        queryWrapper.le(StorageItem::getCreateTime, localDateTime1);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(StorageItem::getAccessoryId, Integer.parseInt(id));
        storageItemList = storageItemService.list(queryWrapper);
        list.add(storageItemList.size());
        price = new BigDecimal(0.0);
        for (StorageItem storageItem : storageItemList) {
            price = price.add(storageItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(StorageItem::getCreateTime, localDateTime1);
        queryWrapper.le(StorageItem::getCreateTime, localDateTime);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(StorageItem::getAccessoryId, Integer.parseInt(id));
        storageItemList = storageItemService.list(queryWrapper);
        list.add(storageItemList.size());
        price = new BigDecimal(0.0);
        for (StorageItem storageItem : storageItemList) {
            price = price.add(storageItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        List list1 = new ArrayList();
        list1.add(bigDecimalList);
        list1.add(list);

        return new Result(true, StatusCode.OK, "查询入库配件数据成功", list1);
    }

    /**
     * 查询使用配件数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询使用配件数据")
    @GetMapping("/getAccessoryMaintenanceStatistic")
    @ApiImplicitParam(name = "id", value = "配件ID", required = true, paramType = "query", dataType = "String")
    public Result getAccessoryMaintenanceStatistic(@RequestParam(value = "id") String id) {

        List<Integer> list = new ArrayList<>();
        List<BigDecimal> bigDecimalList = new ArrayList<>();
        List<MaintenanceItem> maintenanceItemList = new ArrayList<>();
        LambdaQueryWrapper<MaintenanceItem> queryWrapper = new LambdaQueryWrapper<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).plusMonths(1);
        LocalDateTime localDateTime1 = localDateTime.plusMonths(-1);
        LocalDateTime localDateTime2 = localDateTime.plusMonths(-2);
        LocalDateTime localDateTime3 = localDateTime.plusMonths(-3);
        LocalDateTime localDateTime4 = localDateTime.plusMonths(-4);

        queryWrapper.ge(MaintenanceItem::getCreateTime, localDateTime4);
        queryWrapper.le(MaintenanceItem::getCreateTime, localDateTime3);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(MaintenanceItem::getAccessoryId, Integer.parseInt(id));
        maintenanceItemList = maintenanceItemService.list(queryWrapper);
        list.add(maintenanceItemList.size());
        BigDecimal price = new BigDecimal(0.0);
        for (MaintenanceItem maintenanceItem : maintenanceItemList) {
            price = price.add(maintenanceItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(MaintenanceItem::getCreateTime, localDateTime3);
        queryWrapper.le(MaintenanceItem::getCreateTime, localDateTime2);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(MaintenanceItem::getAccessoryId, Integer.parseInt(id));
        maintenanceItemList = maintenanceItemService.list(queryWrapper);
        list.add(maintenanceItemList.size());
        price = new BigDecimal(0.0);
        for (MaintenanceItem maintenanceItem : maintenanceItemList) {
            price = price.add(maintenanceItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(MaintenanceItem::getCreateTime, localDateTime2);
        queryWrapper.le(MaintenanceItem::getCreateTime, localDateTime1);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(MaintenanceItem::getAccessoryId, Integer.parseInt(id));
        maintenanceItemList = maintenanceItemService.list(queryWrapper);
        list.add(maintenanceItemList.size());
        price = new BigDecimal(0.0);
        for (MaintenanceItem maintenanceItem : maintenanceItemList) {
            price = price.add(maintenanceItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(MaintenanceItem::getCreateTime, localDateTime1);
        queryWrapper.le(MaintenanceItem::getCreateTime, localDateTime);
        if (!id.equals("") && !id.equals("undefined"))
            queryWrapper.eq(MaintenanceItem::getAccessoryId, Integer.parseInt(id));
        maintenanceItemList = maintenanceItemService.list(queryWrapper);
        list.add(maintenanceItemList.size());
        price = new BigDecimal(0.0);
        for (MaintenanceItem maintenanceItem : maintenanceItemList) {
            price = price.add(maintenanceItem.getTotalPrice());
        }
        bigDecimalList.add(price);

        List list1 = new ArrayList();
        list1.add(bigDecimalList);
        list1.add(list);

        return new Result(true, StatusCode.OK, "查询使用配件数据成功", list1);
    }


}
