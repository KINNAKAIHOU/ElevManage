package com.scau.zwp.elevmanage;


import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.zwp.elevmanage.controller.ElevatorController;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Storage;
import com.scau.zwp.elevmanage.entity.StorageItem;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.service.StorageService;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ElevManageApplicationTests {

    @Resource
    ElevatorController elevatorController;
    @Resource
    StorageService storageServicer;
    private LocalDateTime LocalDateTime;


    @Test
    void contextLoads() {
//        elevatorController.deleteById(8);
        //        Location location = locationService.getById(elevator.getLocationId());
//        DateUtil.formatDate(new Date(System.currentTimeMillis()));
//        System.out.println(DateUtil.formatDate(new Date(System.currentTimeMillis())));
//        System.out.println(DateUtil.format(new Date(), "yyyyMMdd"));

//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("manufacturer_id", 2);
//        List<Elevator> elevatorList = elevatorMapper.selectList(queryWrapper);
//        System.out.println(elevatorList);


//        Date date = new Date();
//        Instant instant = date.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,zoneId);
//
//        Storage storage = new Storage();
//        storage.setStorageTime(LocalDateTime);
//        storage.setOperatorPerson("mmm");
//        storage.setSupplierName("eeee");
//        storage.setTotalPrice(new BigDecimal(123));
//        List<StorageItem> storageItems = new ArrayList<>();
//        StorageItem storageItem = new StorageItem();
//        storageItem.getAccessoryId();
//        storageServicer.insert(storage,);

        String str = "三星";
//        StringBuilder convert = new StringBuilder();
//        if (StringUtils.isEmpty(str)) {
//            System.out.println(str);
//        }
//        char word = str.charAt(0);
//        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
//        if (pinyinArray != null) {
//            convert.append(pinyinArray[0].charAt(0));
//        } else {
//            convert.append(word);
//        }
//        System.out.println(convert.toString().toUpperCase());

        System.out.println(PinyinUtil.getFirstLetter(str, "").toUpperCase());

    }

}
