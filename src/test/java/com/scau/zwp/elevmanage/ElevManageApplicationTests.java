package com.scau.zwp.elevmanage;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.zwp.elevmanage.controller.ElevatorController;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ElevManageApplicationTests {

    @Resource
    ElevatorController elevatorController;
    @Mapper
    ElevatorMapper elevatorMapper;

    @Test
    void contextLoads() {
//        elevatorController.deleteById(8);
        //        Location location = locationService.getById(elevator.getLocationId());
//        DateUtil.formatDate(new Date(System.currentTimeMillis()));
//        System.out.println(DateUtil.formatDate(new Date(System.currentTimeMillis())));
//        System.out.println(DateUtil.format(new Date(), "yyyyMMdd"));

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("manufacturer_id", 2);
        List<Elevator> elevatorList = elevatorMapper.selectList(queryWrapper);
        System.out.println(elevatorList);
    }

}
