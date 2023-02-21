package com.scau.zwp.elevmanage;

import com.scau.zwp.elevmanage.controller.PlanController;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/21
 */
@Component
@EnableScheduling
public class Crontab {
    @Resource
    private PlanController planController;

    @Scheduled(cron = "${schedules.runBatch}")
    public void crontab() throws Exception {
        System.out.println("hello");
        if (planController.checkAllPlan().getCode() == 1) {
            System.out.println("更新了成功");
        } else
            System.out.println("更新失败");
    }
}
