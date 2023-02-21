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
import com.scau.zwp.elevmanage.mapper.MaintenanceMapper;
import com.scau.zwp.elevmanage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.vo.MaintenanceVo;
import com.scau.zwp.elevmanage.vo.StorageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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

    @Value("${spring.servlet.multipart.location}" + "/maintenance/")
    private String uploadRootPath;


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
     * @return
     */
    public Result paginQuery(Maintenance maintenance, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Maintenance> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(maintenance.getMaintenanceNumber())) {
            queryWrapper.like(Maintenance::getMaintenanceNumber, maintenance.getMaintenanceNumber());
        }
        if (StrUtil.isNotBlank(maintenance.getElevatorNumber())) {
            queryWrapper.like(Maintenance::getElevatorNumber, maintenance.getElevatorNumber());
        }
        if (StrUtil.isNotBlank(maintenance.getLocationName())) {
            queryWrapper.like(Maintenance::getLocationName, maintenance.getLocationName());
        }
        if (StrUtil.isNotBlank(maintenance.getAddress())) {
            queryWrapper.like(Maintenance::getAddress, maintenance.getAddress());
        }
        if (StrUtil.isNotBlank(maintenance.getContactPerson())) {
            queryWrapper.like(Maintenance::getContactPerson, maintenance.getContactPerson());
        }
        if (StrUtil.isNotBlank(maintenance.getIsFinish())) {
            queryWrapper.like(Maintenance::getIsFinish, maintenance.getIsFinish());
        }
        //2. 执行分页查询
        Page<Maintenance> pagin = new Page<>(current, size, true);
        IPage<Maintenance> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询维修报告分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    public Result insert(Maintenance maintenance, List<MaintenanceItem> maintenanceItemList, MultipartFile[] files) {
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
        maintenance.setMaintenanceNumber(number);
        if (save(maintenance) == true) {
            if (insertMaintenanceImage(maintenance.getId(), files).getCode() == 2001)
                return new Result(false, StatusCode.ERROR, "维修报告图片上传失败");
            if (maintenanceItemList != null) {
                for (MaintenanceItem maintenanceItem : maintenanceItemList) {
                    maintenanceItem.setMaintenanceId(maintenance.getId());
                    if (maintenanceItemService.save(maintenanceItem) == true) {
                        if (inventoryService.reduce(maintenanceItem.getAccessoryId(), maintenanceItem.getQuantity()).getCode() != 1)
                            return new Result(false, StatusCode.ERROR, "库存登记失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "维修报告详情登记失败");
                }
            }
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
        if (updateById(maintenance) == true) {
            if (insertMaintenanceImage(maintenance.getId(), files).getCode() == 2001)
                return new Result(false, StatusCode.ERROR, "维修报告图片上传失败");
            if (maintenanceItemList != null) {
                for (MaintenanceItem maintenanceItem : maintenanceItemList) {
                    maintenanceItem.setMaintenanceId(maintenance.getId());
                    if (maintenanceItemService.save(maintenanceItem) == true) {
                        if (inventoryService.reduce(maintenanceItem.getAccessoryId(), maintenanceItem.getQuantity()).getCode() != 1)
                            return new Result(false, StatusCode.ERROR, "库存登记失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "维修报告详情登记失败");
                }
            }
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
        if (maintenanceImageList != null) {
            for (MaintenanceImage maintenanceImage : maintenanceImageList) {
                if (maintenanceImageService.removeById(maintenanceImage.getId()) == false)
                    return new Result(false, StatusCode.ERROR, "删除维修报告图片失败");
            }
        }
        if (maintenanceItemList != null) {
            for (MaintenanceItem maintenanceItem : maintenanceItemList) {
                if (maintenanceItemService.removeById(maintenanceItem) == true) {
                    if (inventoryService.increase(maintenanceItem.getAccessoryId(), maintenanceItem.getQuantity()).getCode() != 1)
                        return new Result(false, StatusCode.ERROR, "删除库存登记失败");
                } else
                    return new Result(false, StatusCode.ERROR, "删除维修报告详情登记失败");
            }
        }
        if (removeById(id) == true)
            return new Result(true, StatusCode.OK, "删除维修报告成功");
        else
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

}
