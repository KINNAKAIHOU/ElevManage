package com.scau.zwp.elevmanage;


import com.scau.zwp.elevmanage.controller.ElevatorController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ElevManageApplicationTests {

    @Resource
    ElevatorController elevatorController;

    @Test
    void contextLoads() {
        elevatorController.deleteById(8);
    }

}
