package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.*;
import com.scau.zwp.elevmanage.mapper.InspectionMapper;
import com.scau.zwp.elevmanage.mapper.MaintenanceMapper;
import com.scau.zwp.elevmanage.service.InspectionImageService;
import com.scau.zwp.elevmanage.service.InspectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.vo.InspectionVo;
import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;
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
 * 检查报告 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection> implements InspectionService {

    @Resource
    private InspectionImageService inspectionImageService;
    @Resource
    private MaintenanceMapper maintenanceMapper;
    @Value("${spring.servlet.multipart.location}" + "/inspection/")
    private String uploadRootPath;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public R<InspectionVo> queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("inspection_id", id);
        List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
        Inspection inspection = getById(id);
        if (inspection == null)
            return R.error("通过ID查询检查报告失败");
        else {
            InspectionVo inspectionVo = BeanUtil.copyProperties(inspection, InspectionVo.class);
            inspectionVo.setInspectionImageList(inspectionImageList);
            return R.success(inspectionVo);
        }
    }

    /**
     * 分页查询
     *
     * @param inspection 筛选条件
     * @param current    当前页码
     * @param size       每页大小
     * @return
     */
    public Page<Inspection> paginQuery(Inspection inspection, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Inspection> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(inspection.getInspectionNumber())) {
            queryWrapper.like(Inspection::getInspectionNumber, inspection.getInspectionNumber());
        }
        if (StrUtil.isNotBlank(inspection.getElevatorNumber())) {
            queryWrapper.like(Inspection::getElevatorNumber, inspection.getElevatorNumber());
        }
        if (StrUtil.isNotBlank(inspection.getLocationName())) {
            queryWrapper.like(Inspection::getLocationName, inspection.getLocationName());
        }
        if (StrUtil.isNotBlank(inspection.getAddress())) {
            queryWrapper.like(Inspection::getAddress, inspection.getAddress());
        }
        if (StrUtil.isNotBlank(inspection.getContactPerson())) {
            queryWrapper.like(Inspection::getContactPerson, inspection.getContactPerson());
        }
        if (StrUtil.isNotBlank(inspection.getInspectionPerson())) {
            queryWrapper.like(Inspection::getInspectionPerson, inspection.getInspectionPerson());
        }
        if (StrUtil.isNotBlank(inspection.getIsFinish())) {
            queryWrapper.like(Inspection::getIsFinish, inspection.getIsFinish());
        }
        if (StrUtil.isNotBlank(inspection.getIsFault())) {
            queryWrapper.like(Inspection::getIsFault, inspection.getIsFault());
        }
        //2. 执行分页查询
        Page<Inspection> pagin = new Page<>(current, size, true);
        IPage<Inspection> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    public R<Boolean> insert(Inspection inspection, MultipartFile[] files) {
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = "JC" + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<Inspection> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("inspection_number", number);
            if (getOne(queryWrapper) != null)
                continue;
            else
                break;
        }
        inspection.setInspectionNumber(number);
        if (save(inspection) == true) {
            if (insertInspectionImage(inspection.getId(), files).getCode() == 1)
                return R.success(true);
            else
                return R.error("电梯图片上传失败");
        } else
            return R.error("新增数据失败");
    }

    /**
     * 更新数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(Inspection inspection, MultipartFile[] files) {
        if (updateById(inspection) == true) {
            if (inspection.getIsFinish() == "1") {
                if (finish(inspection).getCode() == 0)
                    return R.error("完成失败");
            }
            if (insertInspectionImage(inspection.getId(), files).getCode() == 1)
                return R.success(true);
            else
                return R.error("电梯图片上传失败");
        } else
            return R.error("更新数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public R<Boolean> deleteById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("inspection_id", id);
        List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
        if (inspectionImageList != null) {
            for (InspectionImage inspectionImage : inspectionImageList) {
                Integer maintenanceId = inspectionImage.getMaintenanceId();
                if (maintenanceMapper.selectById(maintenanceId) == null) {
                    if (inspectionImageService.removeById(inspectionImage.getId()) == false)
                        return R.error("删除检查图片失败");
                }
            }
        }
        if (removeById(id) == true)
            return R.success(true);
        else
            return R.error("通过主键删除数据失败");
    }

    /**
     * 添加图片
     *
     * @param files 图片
     * @return 是否成功
     */
    public R<Boolean> insertInspectionImage(Integer id, MultipartFile[] files) {
        if (files == null)
            return R.success(true);
        for (MultipartFile file : files) {
            String orgName = file.getOriginalFilename();
            String extName = orgName.substring(orgName.lastIndexOf('.'));
            String destName = UUID.randomUUID().toString().toUpperCase() + extName;
            try {
                file.transferTo(new File(uploadRootPath, destName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return R.error("检查报告图片上传失败");
            }
            InspectionImage inspectionImage = new InspectionImage();
            inspectionImage.setInspectionId(id);
            inspectionImage.setImageName(destName);
            inspectionImage.setImagePosition("/inspection/" + destName);
            if (inspectionImageService.save(inspectionImage) == false)
                return R.error("检查报告图片上传失败");
        }
        return R.success(true);
    }

    /**
     * 确认完成
     *
     * @param inspection 主键
     * @return 是否成功
     */
    public R<Boolean> finish(Inspection inspection) {
        Maintenance maintenance = new Maintenance();
        maintenance.setElevatorNumber(inspection.getElevatorNumber());
        maintenance.setLocationName(inspection.getLocationName());
        maintenance.setAddress(inspection.getAddress());
        maintenance.setContactNumber(inspection.getContactNumber());
        maintenance.setContactPerson(inspection.getContactPerson());
        maintenance.setCheckDescription(inspection.getCheckDescription());
        maintenance.setElevatorId(inspection.getElevatorId());
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = "WX" + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("maintenance_number", number);
            if (maintenanceMapper.selectOne(queryWrapper) != null)
                continue;
            else
                break;

        }
        maintenance.setMaintenanceNumber(number);
        if (maintenanceMapper.insert(maintenance) != 0) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("inspection_id", inspection.getId());
            List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
            if (inspectionImageList != null) {
                for (InspectionImage inspectionImage : inspectionImageList) {
                    inspectionImage.setMaintenanceId(maintenance.getId());
                    if (!inspectionImageService.updateById(inspectionImage)) {
                        return R.error("添加图片失败");
                    }
                }
            }
        }
        return R.success(true);
    }

}
