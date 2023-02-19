package com.scau.zwp.elevmanage;


import cn.hutool.core.date.DateUtil;
import com.scau.zwp.elevmanage.controller.ElevatorController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ElevManageApplicationTests {

    @Resource
    ElevatorController elevatorController;

    @Test
    void contextLoads() {
//        elevatorController.deleteById(8);
        //        Location location = locationService.getById(elevator.getLocationId());
        DateUtil.formatDate(new Date(System.currentTimeMillis()));
        System.out.println(DateUtil.formatDate(new Date(System.currentTimeMillis())));
        System.out.println(DateUtil.format(new Date(), "yyyyMMdd"));

    }

}
