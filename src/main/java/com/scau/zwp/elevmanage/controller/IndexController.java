package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.zwp.elevmanage.common.*;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.mapper.MaintenanceMapper;
import com.scau.zwp.elevmanage.mapper.StorageMapper;
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
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @Resource
    private StorageMapper storageMapper;
    @Resource
    private MaintenanceMapper maintenanceMapper;
    @Resource
    private UserService userService;

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

    /**
     * 获取PieChart数据
     *
     * @return 是否成功
     */
    @ApiOperation("获取PieChart数据")
    @GetMapping("/getPieChart")
    public Result getPieChart() {
        List<PieChart> pieCharts = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", "正常");
        PieChart pieChart = new PieChart();
        pieChart.setName("正常");
        pieChart.setValue(elevatorService.list(queryWrapper).size());
        pieCharts.add(pieChart);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("status", "待检查");
        PieChart pieChart1 = new PieChart();
        pieChart1.setName("待检查");
        pieChart1.setValue(elevatorService.list(queryWrapper1).size());
        pieCharts.add(pieChart1);

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("status", "待维修");
        PieChart pieChart2 = new PieChart();
        pieChart2.setName("待维修");
        pieChart2.setValue(elevatorService.list(queryWrapper2).size());
        pieCharts.add(pieChart2);
        return new Result(true, StatusCode.OK, "查询PieChart数据成功", pieCharts);
    }


    /**
     * 获取BarChart数据
     *
     * @return 是否成功
     */
    @ApiOperation("获取BarChart数据")
    @GetMapping("/getBarChart")
    public Result getBarChart() {
        List<Double> doubles1 = new ArrayList<>();
        List<Double> doubles2 = new ArrayList<>();
        List<Double> doubles3 = new ArrayList<>();
        List<Double> doubles4 = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        Date startOfMonth = Date.valueOf(String.format("%04d-%02d-01", year, month));
        Date endOfMonth = Date.valueOf(String.format("%04d-%02d-%02d", year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.MONTH, -1);
        Date startOfLastMonth = Date.valueOf(String.format("%04d-%02d-01", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1));
        Date endOfLastMonth = Date.valueOf(String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.getActualMaximum(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.MONTH, -1);
        Date startOfLast2Month = Date.valueOf(String.format("%04d-%02d-01", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1));
        Date endOfLast2Month = Date.valueOf(String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.getActualMaximum(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.MONTH, -1);
        Date startOfLast3Month = Date.valueOf(String.format("%04d-%02d-01", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1));
        Date endOfLast3Month = Date.valueOf(String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.getActualMaximum(Calendar.DAY_OF_MONTH)));
        Double totalOfThis1Month = storageMapper.selectTotalPriceBetween(startOfMonth, endOfMonth) == null ? 0.0 : storageMapper.selectTotalPriceBetween(startOfMonth, endOfMonth);
        Double totalOfLast2Month = storageMapper.selectTotalPriceBetween(startOfLastMonth, endOfLastMonth) == null ? 0.0 : storageMapper.selectTotalPriceBetween(startOfLastMonth, endOfLastMonth);
        Double totalOfLast3Month = storageMapper.selectTotalPriceBetween(startOfLast2Month, endOfLast2Month) == null ? 0.0 : storageMapper.selectTotalPriceBetween(startOfLast2Month, endOfLast2Month);
        Double totalOfLast4Month = storageMapper.selectTotalPriceBetween(startOfLast3Month, endOfLast3Month) == null ? 0.0 : storageMapper.selectTotalPriceBetween(startOfLast3Month, endOfLast3Month);
        if (totalOfLast4Month == 0.0)
            doubles3.add(0.0);
        else
            doubles3.add(((totalOfLast3Month - totalOfLast4Month) / totalOfLast4Month) * 100);
        doubles1.add(totalOfLast3Month);
        if (totalOfLast3Month == 0.0)
            doubles3.add(0.0);
        else
            doubles3.add(((totalOfLast2Month - totalOfLast3Month) / totalOfLast3Month) * 100);
        doubles1.add(totalOfLast2Month);
        if (totalOfLast2Month == 0.0)
            doubles3.add(0.0);
        else
            doubles3.add(((totalOfThis1Month - totalOfLast2Month) / totalOfLast2Month) * 100);
        doubles1.add(totalOfThis1Month);

        totalOfThis1Month = maintenanceMapper.selectTotalPriceBetween(startOfMonth, endOfMonth) == null ? 0.0 : maintenanceMapper.selectTotalPriceBetween(startOfMonth, endOfMonth);
        totalOfLast2Month = maintenanceMapper.selectTotalPriceBetween(startOfLastMonth, endOfLastMonth) == null ? 0.0 : maintenanceMapper.selectTotalPriceBetween(startOfLastMonth, endOfLastMonth);
        totalOfLast3Month = maintenanceMapper.selectTotalPriceBetween(startOfLast2Month, endOfLast2Month) == null ? 0.0 : maintenanceMapper.selectTotalPriceBetween(startOfLast2Month, endOfLast2Month);
        totalOfLast4Month = maintenanceMapper.selectTotalPriceBetween(startOfLast3Month, endOfLast3Month) == null ? 0.0 : maintenanceMapper.selectTotalPriceBetween(startOfLast3Month, endOfLast3Month);
        if (totalOfLast4Month == 0.0)
            doubles4.add(0.0);
        else
            doubles4.add(((totalOfLast3Month - totalOfLast4Month) / totalOfLast4Month) * 100);
        doubles2.add(totalOfLast3Month);
        if (totalOfLast3Month == 0.0)
            doubles4.add(0.0);
        else
            doubles4.add(((totalOfLast2Month - totalOfLast3Month) / totalOfLast3Month) * 100);
        doubles2.add(totalOfLast2Month);
        if (totalOfLast2Month == 0.0)
            doubles4.add(0.0);
        else
            doubles4.add(((totalOfThis1Month - totalOfLast2Month) / totalOfLast2Month) * 100);
        doubles2.add(totalOfThis1Month);
        List list = new ArrayList();
        list.add(doubles1);
        list.add(doubles2);
        list.add(doubles3);
        list.add(doubles4);
        return new Result(true, StatusCode.OK, "查询BarChart数据成功", list);
    }


    /**
     * 获取RadarChart数据
     *
     * @return 是否成功
     */
    @ApiOperation("获取RadarChart数据")
    @GetMapping("/getRadarChart")
    public Result getRadarChart() {
        List<Integer> list = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("permission_id", "1");
        list.add((int) userService.count(queryWrapper));
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("permission_id", "2");
        list.add((int) userService.count(queryWrapper1));
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("permission_id", "3");
        list.add((int) userService.count(queryWrapper2));
        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("permission_id", "4");
        list.add((int) userService.count(queryWrapper3));
        RadarChart radarChart = new RadarChart();
        radarChart.setName("用户数量");
        radarChart.setValue(list);
        return new Result(true, StatusCode.OK, "查询PieChart数据成功", radarChart);
    }

}
