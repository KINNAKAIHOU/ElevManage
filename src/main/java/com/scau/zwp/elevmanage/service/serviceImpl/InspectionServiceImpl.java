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
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.mapper.InspectionMapper;
import com.scau.zwp.elevmanage.mapper.MaintenanceMapper;
import com.scau.zwp.elevmanage.service.InspectionImageService;
import com.scau.zwp.elevmanage.service.InspectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.service.MessageService;
import com.scau.zwp.elevmanage.vo.InspectionVo;
import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Resource
    private ElevatorMapper elevatorMapper;
    @Resource
    private MessageService messageService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("inspection_id", id);
        List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
        Inspection inspection = getById(id);
        if (inspection == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询检查报告信息失败");
        else {
            InspectionVo inspectionVo = BeanUtil.copyProperties(inspection, InspectionVo.class);
            inspectionVo.setInspectionImageList(inspectionImageList);
            return new Result(true, StatusCode.OK, "通过ID查询检查报告信息成功", inspectionVo);
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
    public Result paginQuery(Inspection inspection, Integer current, Integer size, String startTime, String endTime) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Inspection> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(inspection.getInspectionNumber())) {
            queryWrapper.like(Inspection::getInspectionNumber, inspection.getInspectionNumber());
        }
        if (StrUtil.isNotBlank(inspection.getElevatorNumber())) {
            queryWrapper.like(Inspection::getElevatorNumber, inspection.getElevatorNumber());
        }
        if (StrUtil.isNotBlank(inspection.getElevatorName())) {
            queryWrapper.like(Inspection::getElevatorName, inspection.getElevatorName());
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
        if (StrUtil.isNotBlank(inspection.getCheckDescription())) {
            queryWrapper.like(Inspection::getCheckDescription, inspection.getCheckDescription());
        }
        if (StrUtil.isNotBlank(inspection.getInspectionPerson())) {
            queryWrapper.like(Inspection::getInspectionPerson, inspection.getInspectionPerson());
        }
        if (StrUtil.isNotBlank(inspection.getIsFinish())) {
            queryWrapper.eq(Inspection::getIsFinish, inspection.getIsFinish());
        }
        if (StrUtil.isNotBlank(inspection.getIsFault())) {
            queryWrapper.eq(Inspection::getIsFault, inspection.getIsFault());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (startTime != "") {
            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
            queryWrapper.ge(Inspection::getInspectionData, startDateTime);
        }
        if (endTime != "") {
            LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
            queryWrapper.le(Inspection::getInspectionData, endDateTime);
        }
        //2. 执行分页查询
        Page<Inspection> pagin = new Page<>(current, size, true);
        IPage<Inspection> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询检查报告分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    public Result insert(Inspection inspection, MultipartFile[] files) {
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
        Elevator elevator = elevatorMapper.selectById(inspection.getElevatorId());
        inspection.setElevatorNumber(elevator.getElevatorNumber());
        inspection.setElevatorName(elevator.getElevatorName());
        inspection.setLocationName(elevator.getLocationName());
        inspection.setAddress(elevator.getAddress());
        inspection.setContactPerson(elevator.getContactPerson());
        inspection.setContactNumber(elevator.getContactNumber());
        inspection.setElevatorId(elevator.getId());
        if (elevator.getStatus().equals("正常"))
            elevator.setStatus("待检查");
        elevatorMapper.updateById(elevator);//修改电梯的状态
        if (save(inspection) == true) {
            if (insertInspectionImage(inspection.getId(), files).getCode() == 2000) {
                Message message = new Message();
                message.setMessage("添加新检查报告  " + inspection.getInspectionNumber() + "--" + inspection.getElevatorNumber() + ":" + inspection.getElevatorName());
                messageService.save(message);
                return new Result(true, StatusCode.OK, "添加检查报告成功");
            } else
                return new Result(false, StatusCode.ERROR, "检查报告图片上传失败");
        } else
            return new Result(false, StatusCode.ERROR, "添加检查报告失败");
    }

    /**
     * 更新数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    public Result update(Inspection inspection, MultipartFile[] files) {
        inspection.setIsFinish("1");
        if (updateById(inspection) == true) {
            inspection = getById(inspection.getId());
            Elevator elevator = elevatorMapper.selectById(inspection.getElevatorId());
            if (elevator.getStatus().equals("待检查")) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("is_finish", "0");
                queryWrapper.eq("elevator_id", inspection.getElevatorId());
                if (list(queryWrapper).size() == 0) {
                    elevator.setStatus("正常");
                    elevatorMapper.updateById(elevator);//修改电梯的状态
                }
            }
            if (insertInspectionImage(inspection.getId(), files).getCode() == 2000) {
                Message message = new Message();
                message.setMessage(inspection.getInspectionPerson() + "  完成检查报告  " + inspection.getInspectionNumber() + "--" + inspection.getElevatorNumber() + ":" + inspection.getElevatorName());
                messageService.save(message);
                if (inspection.getIsFault().equals("1")) {
                    if (finish(inspection).getCode() == 2001)
                        return new Result(false, StatusCode.ERROR, "检查报告完成失败");
                }
                return new Result(true, StatusCode.OK, "更新检查报告数据成功");
            } else
                return new Result(false, StatusCode.ERROR, "检查报告图片上传失败");
        } else
            return new Result(false, StatusCode.ERROR, "更新检查报告数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("inspection_id", id);
        List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
        if (inspectionImageList != null) {
            for (InspectionImage inspectionImage : inspectionImageList) {
                Integer maintenanceId = inspectionImage.getMaintenanceId();
                if (maintenanceMapper.selectById(maintenanceId) == null) {
                    if (inspectionImageService.removeById(inspectionImage.getId()) == false)
                        return new Result(false, StatusCode.ERROR, "删除检查报告图片失败");
                }
            }
        }
        Elevator elevator = elevatorMapper.selectById(id);
        Inspection inspection = getById(id);
        if (elevator.getStatus().equals("待检查") && inspection.getIsFinish() == "0") {
            QueryWrapper neQueryWrapper = new QueryWrapper();
            neQueryWrapper.eq("is_finish", "0");
            queryWrapper.eq("elevator_id", inspection.getElevatorId());
            if (list(queryWrapper).size() == 1) {
                elevator.setStatus("正常");
                elevatorMapper.updateById(elevator);//修改电梯的状态
            }
        }
        if (removeById(id) == true) {
            Message message = new Message();
            message.setMessage("删除检查报告  " + inspection.getInspectionNumber() + "--" + inspection.getElevatorNumber() + ":" + inspection.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "删除检查报告成功");
        } else
            return new Result(false, StatusCode.ERROR, "删除检查报告失败");
    }

    /**
     * 添加图片
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
                file.transferTo(new File(uploadRootPath, destName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
            }
            InspectionImage inspectionImage = new InspectionImage();
            inspectionImage.setInspectionId(id);
            inspectionImage.setImageName(destName);
            inspectionImage.setImagePosition("/inspection/" + destName);
            if (inspectionImageService.save(inspectionImage) == false)
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
        }
        return new Result(true, StatusCode.OK, "图片上传成功");
    }

    /**
     * 确认完成
     *
     * @param inspection 主键
     * @return 是否成功
     */
    public Result finish(Inspection inspection) {
        Maintenance maintenance = new Maintenance();
        maintenance.setElevatorNumber(inspection.getElevatorNumber());
        maintenance.setElevatorName(inspection.getElevatorName());
        maintenance.setLocationName(inspection.getLocationName());
        maintenance.setAddress(inspection.getAddress());
        maintenance.setContactPerson(inspection.getContactPerson());
        maintenance.setContactNumber(inspection.getContactNumber());
        maintenance.setCheckDescription(inspection.getCheckDescription());
        maintenance.setElevatorId(inspection.getElevatorId());
        maintenance.setApplicant(inspection.getInspectionPerson());
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
        Elevator elevator = elevatorMapper.selectById(inspection.getElevatorId());
        elevator.setStatus("待维修");
        elevatorMapper.updateById(elevator);
        maintenance.setMaintenanceNumber(number);
        if (maintenanceMapper.insert(maintenance) != 0) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("inspection_id", inspection.getId());
            List<InspectionImage> inspectionImageList = inspectionImageService.list(queryWrapper);
            if (inspectionImageList != null) {
                for (InspectionImage inspectionImage : inspectionImageList) {
                    inspectionImage.setMaintenanceId(maintenance.getId());
                    if (!inspectionImageService.updateById(inspectionImage)) {
                        return new Result(false, StatusCode.ERROR, "维修报告绑定检查图片失败");
                    }
                }
            }
            {
                Message message = new Message();
                message.setMessage("添加新检查报告  " + maintenance.getMaintenanceNumber() + "--" + maintenance.getElevatorNumber() + ":" + maintenance.getElevatorName());
                messageService.save(message);
                return new Result(true, StatusCode.OK, "生成维修报告成功");
            }

        } else
            return new Result(false, StatusCode.ERROR, "生成维修报告失败");
    }

}
