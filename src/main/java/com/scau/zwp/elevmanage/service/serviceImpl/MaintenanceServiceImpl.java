package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.*;
import com.scau.zwp.elevmanage.mapper.AccessoryMapper;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.mapper.InspectionMapper;
import com.scau.zwp.elevmanage.mapper.MaintenanceMapper;
import com.scau.zwp.elevmanage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.vo.InspectionVo;
import com.scau.zwp.elevmanage.vo.MaintenanceVo;
import com.scau.zwp.elevmanage.vo.StorageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 维修报告 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements MaintenanceService {
    @Resource
    private MaintenanceImageService maintenanceImageService;
    @Resource
    private MaintenanceItemService maintenanceItemService;
    @Resource
    private InspectionImageService inspectionImageService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private ElevatorMapper elevatorMapper;
    @Resource
    private InspectionMapper inspectionMapper;
    @Resource
    private MessageService messageService;
    @Resource
    private AccessoryMapper accessoryMapper;

    @Value("${spring.servlet.multipart.location}" + "/maintenance/")
    private String uploadRootPath;

    @Value("${spring.servlet.multipart.location}" + "/inspection/")
    private String uploadInspectionRootPath;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("maintenance_id", id);
        List<MaintenanceImage> maintenanceImageList = maintenanceImageService.list(queryWrapper);
        List<MaintenanceItem> maintenanceItemList = maintenanceItemService.list(queryWrapper);
        List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
        Maintenance maintenance = getById(id);
        if (maintenance == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询维修报告信息失败");
        else {
            MaintenanceVo maintenanceVo = BeanUtil.copyProperties(maintenance, MaintenanceVo.class);
            maintenanceVo.setMaintenanceImageList(maintenanceImageList);
            maintenanceVo.setMaintenanceItemList(maintenanceItemList);
            maintenanceVo.setInspectionImageList(inspectionImageList);
            return new Result(true, StatusCode.OK, "通过ID查询维修报告信息成功", maintenanceVo);
        }
    }


    /**
     * 分页查询
     *
     * @param maintenance 筛选条件
     * @param current     当前页码
     * @param size        每页大小
     * @param startTime   开启日期
     * @param endTime     结束日期
     * @return
     */
    public Result paginQuery(Maintenance maintenance, Integer current, Integer size, String startTime, String endTime) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Maintenance> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(maintenance.getLocationName())) {
            queryWrapper.like(Maintenance::getLocationName, maintenance.getLocationName());
        }
        if (StrUtil.isNotBlank(maintenance.getMaintenancePerson())) {
            queryWrapper.like(Maintenance::getMaintenancePerson, maintenance.getMaintenancePerson());
        }
        if (StrUtil.isNotBlank(maintenance.getIsFinish())) {
            queryWrapper.eq(Maintenance::getIsFinish, maintenance.getIsFinish());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (startTime != null && startTime != "") {
            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
            queryWrapper.ge(Maintenance::getMaintenanceData, startDateTime);
        }
        if (endTime != null && startTime != "") {
            LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
            queryWrapper.le(Maintenance::getMaintenanceData, endDateTime);
        }
        if (maintenance.getElevatorId() != null) {
            queryWrapper.eq(Maintenance::getElevatorId, maintenance.getElevatorId());
        }
        //2. 执行分页查询
        Page<Maintenance> pagin = new Page<>(current, size, true);
        IPage<Maintenance> selectResult = page(pagin, queryWrapper);
        List<Maintenance> maintenanceList = selectResult.getRecords();
        List<MaintenanceVo> maintenanceVoList = new ArrayList<>();
        for (Maintenance maintenance1 : maintenanceList) {
            QueryWrapper newQueryWrapper = new QueryWrapper();
            newQueryWrapper.eq("maintenance_id", maintenance1.getId());
            List<MaintenanceImage> maintenanceImageList = maintenanceImageService.list(newQueryWrapper);
            QueryWrapper newQueryWrapper1 = new QueryWrapper();
            newQueryWrapper1.eq("maintenance_id", maintenance1.getId());
            List<InspectionImage> inspectionImageList = inspectionImageService.list(newQueryWrapper);
            MaintenanceVo maintenanceVo = BeanUtil.copyProperties(maintenance1, MaintenanceVo.class);
            maintenanceVo.setInspectionImageList(inspectionImageList);
            maintenanceVo.setMaintenanceImageList(maintenanceImageList);
            maintenanceVoList.add(maintenanceVo);
        }
        Page<MaintenanceVo> paginVo = new Page<>(current, size, true);
        paginVo.setPages(selectResult.getPages());
        paginVo.setTotal(selectResult.getTotal());
        paginVo.setRecords(maintenanceVoList);
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询维修报告分页成功", paginVo);
    }

    /**
     * 新增数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    public Result insert(Maintenance maintenance, MultipartFile[] files) {
        Elevator elevator = elevatorMapper.selectById(maintenance.getElevatorId());
        maintenance.setElevatorNumber(elevator.getElevatorNumber());
        maintenance.setElevatorName(elevator.getElevatorName());
        maintenance.setLocationName(elevator.getLocationName());
        maintenance.setAddress(elevator.getAddress());
        maintenance.setContactPerson(elevator.getContactPerson());
        maintenance.setContactNumber(elevator.getContactNumber());
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = "WX" + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("maintenance_number", number);
            if (getOne(queryWrapper) != null)
                continue;
            else
                break;
        }
        elevator.setStatus("待维修");
        elevatorMapper.updateById(elevator);
        maintenance.setMaintenanceNumber(number);
        if (save(maintenance) == true) {
            if (insertInspectionImage(maintenance.getId(), files).getCode() == 2001)
                return new Result(false, StatusCode.ERROR, "检查报告图片上传失败");
//            if (maintenanceItemList != null) {
//                for (MaintenanceItem maintenanceItem : maintenanceItemList) {
//                    maintenanceItem.setMaintenanceId(maintenance.getId());
//                    if (maintenanceItemService.save(maintenanceItem) == true) {
//                        if (inventoryService.reduce(maintenanceItem.getAccessoryId(), maintenanceItem.getQuantity()).getCode() != 1)
//                            return new Result(false, StatusCode.ERROR, "库存登记失败");
//                    } else
//                        return new Result(false, StatusCode.ERROR, "维修报告详情登记失败");
//                }
//            }
            Message message = new Message();
            message.setMessage("添加维修报告  " + maintenance.getMaintenanceNumber() + "--" + maintenance.getElevatorNumber() + ":" + maintenance.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "添加维修报告成功");
        } else
            return new Result(false, StatusCode.ERROR, "添加维修报告失败");
    }


    /**
     * 更新数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    public Result update(Maintenance maintenance, List<MaintenanceItem> maintenanceItemList, MultipartFile[] files) {
        maintenance.setIsFinish("1");
        if (updateById(maintenance) == true) {
            maintenance = getById(maintenance.getId());
            if (insertMaintenanceImage(maintenance.getId(), files).getCode() == 2001)
                return new Result(false, StatusCode.ERROR, "维修报告图片上传失败");
            if (maintenanceItemList != null) {
                for (MaintenanceItem maintenanceItem : maintenanceItemList) {
                    Accessory accessory = accessoryMapper.selectById(maintenanceItem.getAccessoryId());
                    maintenanceItem.setMaintenanceId(maintenance.getId());
                    maintenanceItem.setAccessoryNumber(accessory.getAccessoryNumber());
                    maintenanceItem.setAccessoryName(accessory.getAccessoryName());
                    maintenanceItem.setSpecification(accessory.getSpecification());
                    maintenanceItem.setType(accessory.getType());
                    maintenanceItem.setUnit(accessory.getUnit());
                    maintenanceItem.setCreateTime(maintenance.getMaintenanceData());
                    if (maintenanceItemService.save(maintenanceItem) == true) {
                        if (inventoryService.reduce(maintenanceItem.getAccessoryId(), maintenanceItem.getPurchases()).getCode() != 2000)
                            return new Result(false, StatusCode.ERROR, "库存登记失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "维修报告详情登记失败");
                }
            }
            Elevator elevator = elevatorMapper.selectById(maintenance.getElevatorId());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_finish", "0");
            queryWrapper.eq("elevator_id", maintenance.getElevatorId());
            if (list(queryWrapper).size() == 0) {
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper.eq("is_finish", "0");
                queryWrapper.eq("elevator_id", maintenance.getElevatorId());
                if (inspectionMapper.selectList(queryWrapper).size() == 0)
                    elevator.setStatus("正常");
                else elevator.setStatus("待维修");
                elevatorMapper.updateById(elevator);
            }
            Message message = new Message();
            message.setMessage(maintenance.getMaintenancePerson() + "  完成维修报告  " + maintenance.getMaintenanceNumber() + "--" + maintenance.getElevatorNumber() + ":" + maintenance.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "更新维修报告成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新维修报告失败");
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("maintenance_id", id);
        List<MaintenanceImage> maintenanceImageList = maintenanceImageService.list(queryWrapper);
        List<MaintenanceItem> maintenanceItemList = maintenanceItemService.list(queryWrapper);
        List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
        if (maintenanceImageList.size() != 0) {
            for (MaintenanceImage maintenanceImage : maintenanceImageList) {
                if (maintenanceImageService.removeById(maintenanceImage.getId()) == false)
                    return new Result(false, StatusCode.ERROR, "删除维修报告图片失败");
            }
        }
        if (maintenanceItemList.size() != 0) {
            for (MaintenanceItem maintenanceItem : maintenanceItemList) {
                if (maintenanceItemService.removeById(maintenanceItem) == true) {
                    if (inventoryService.increase(maintenanceItem.getAccessoryId(), maintenanceItem.getPurchases()).getCode() != 1)
                        return new Result(false, StatusCode.ERROR, "删除库存登记失败");
                } else
                    return new Result(false, StatusCode.ERROR, "删除维修报告详情登记失败");
            }
        }
        if (inspectionImageList.size() != 0) {
            for (InspectionImage inspectionImage : inspectionImageList) {
                if (inspectionImage.getMaintenanceId() != null) {
                    if (inspectionMapper.selectById(inspectionImage.getMaintenanceId()) == null)
                        inspectionImageService.removeById(inspectionImage.getId());
                } else
                    inspectionImageService.removeById(inspectionImage.getId());
            }
        }
        Maintenance maintenance = getById(id);
        Elevator elevator = elevatorMapper.selectById(maintenance.getElevatorId());
        if (maintenance.getIsFinish().equals("0")) {
            QueryWrapper newqueryWrapper = new QueryWrapper();
            queryWrapper.eq("is_finish", "0");
            queryWrapper.eq("elevator_id", maintenance.getElevatorId());
            if (list(newqueryWrapper).size() == 0) {
                QueryWrapper newqueryWrapper1 = new QueryWrapper();
                queryWrapper.eq("is_finish", "0");
                queryWrapper.eq("elevator_id", maintenance.getElevatorId());
                if (inspectionMapper.selectList(newqueryWrapper1).size() == 0)
                    elevator.setStatus("正常");
                else elevator.setStatus("待维修");
                elevatorMapper.updateById(elevator);
            }
        }
        if (removeById(id) == true) {
            Message message = new Message();
            message.setMessage("删除维修报告  " + maintenance.getMaintenanceNumber() + "--" + maintenance.getElevatorNumber() + ":" + maintenance.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "删除维修报告成功");
        } else
            return new Result(false, StatusCode.ERROR, "删除维修报告失败");
    }


    /**
     * 添加图片
     *
     * @param files 图片
     * @return 是否成功
     */
    public Result insertMaintenanceImage(Integer id, MultipartFile[] files) {
        if (files == null)
            return new Result(true, StatusCode.OK, "图片数量为0");
        for (MultipartFile file : files) {
            String orgName = file.getOriginalFilename();
            String extName = orgName.substring(orgName.lastIndexOf('.'));
            String destName = UUID.randomUUID().toString().toUpperCase() + extName;
            try {
                file.transferTo(new File(uploadRootPath, destName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return new Result(false, StatusCode.ERROR, "维修报告图片上传失败");
            }
            MaintenanceImage maintenanceImage = new MaintenanceImage();
            maintenanceImage.setMaintenanceId(id);
            maintenanceImage.setImageName(destName);
            maintenanceImage.setImagePosition("/maintenance/" + destName);
            if (maintenanceImageService.save(maintenanceImage) == false)
                return new Result(false, StatusCode.ERROR, "维修报告图片上传失败");
        }
        return new Result(true, StatusCode.OK, "图片上传成功");
    }

    /**
     * 添加检查图片
     *
     * @param files 图片
     * @return 是否成功
     */
    public Result insertInspectionImage(Integer id, MultipartFile[] files) {
        if (files == null)
            return new Result(true, StatusCode.OK, "图片数量为0");
        for (MultipartFile file : files) {
            String orgName = file.getOriginalFilename();
            String extName = orgName.substring(orgName.lastIndexOf('.'));
            String destName = UUID.randomUUID().toString().toUpperCase() + extName;
            try {
                file.transferTo(new File(uploadInspectionRootPath, destName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
            }
            InspectionImage inspectionImage = new InspectionImage();
            inspectionImage.setMaintenanceId(id);
            inspectionImage.setImageName(destName);
            inspectionImage.setImagePosition("/inspection/" + destName);
            if (inspectionImageService.save(inspectionImage) == false)
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
        }
        return new Result(true, StatusCode.OK, "图片上传成功");
    }

}
