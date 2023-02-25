package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.cell.CellSetter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.*;
import com.scau.zwp.elevmanage.mapper.ElevatorImageMapper;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.vo.ElevatorVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 电梯 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class ElevatorServiceImpl extends ServiceImpl<ElevatorMapper, Elevator> implements ElevatorService {
    @Resource
    private ElevatorMapper elevatorMapper;
    @Resource
    private ElevatorImageService elevatorImageService;
    @Resource
    private ManufacturerService manufacturerService;
    @Resource
    private LocationService locationService;
    @Resource
    private MessageService messageService;

    @Value("${spring.servlet.multipart.location}" + "/elevator/")
    private String uploadRootPath;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("elevator_id", id);
        List<ElevatorImage> elevatorImageList = elevatorImageService.list(queryWrapper);
        Elevator elevator = getById(id);
        if (elevator == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询电梯信息失败");
        else {
            ElevatorVo elevatorVo = BeanUtil.copyProperties(elevator, ElevatorVo.class);
            elevatorVo.setElevatorImageList(elevatorImageList);
            return new Result(true, StatusCode.OK, "通过ID查询电梯信息成功", elevatorVo);
        }
    }

    /**
     * 分页查询
     *
     * @param elevator 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Result paginQuery(Elevator elevator, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Elevator> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(elevator.getElevatorNumber())) {
            queryWrapper.like(Elevator::getElevatorNumber, elevator.getElevatorNumber());
        }
        if (StrUtil.isNotBlank(elevator.getElevatorName())) {
            queryWrapper.like(Elevator::getElevatorName, elevator.getElevatorName());
        }
        if (StrUtil.isNotBlank(elevator.getStatus())) {
            queryWrapper.eq(Elevator::getStatus, elevator.getStatus());
        }
        if (StrUtil.isNotBlank(elevator.getLocationName())) {
            queryWrapper.like(Elevator::getLocationName, elevator.getLocationName());
        }
        if (StrUtil.isNotBlank(elevator.getAddress())) {
            queryWrapper.like(Elevator::getAddress, elevator.getAddress());
        }
        if (StrUtil.isNotBlank(elevator.getContactPerson())) {
            queryWrapper.like(Elevator::getContactPerson, elevator.getContactPerson());
        }
        if (StrUtil.isNotBlank(elevator.getManufacturerName())) {
            queryWrapper.like(Elevator::getManufacturerName, elevator.getManufacturerName());
        }
        if (StrUtil.isNotBlank(elevator.getModel())) {
            queryWrapper.like(Elevator::getModel, elevator.getModel());
        }
        if (StrUtil.isNotBlank(elevator.getProductNumber())) {
            queryWrapper.like(Elevator::getProductNumber, elevator.getProductNumber());
        }
        //2. 执行分页查询
        Page<Elevator> pagin = new Page<>(current, size, true);
        IPage<Elevator> selectResult = page(pagin, queryWrapper);
        List<Elevator> elevatorList = selectResult.getRecords();
        for (Elevator elevator1 : elevatorList) {
            QueryWrapper newQueryWrapper = new QueryWrapper();
            newQueryWrapper.eq("elevator_id", elevator1.getId());
            List<ElevatorImage> elevatorImageList = elevatorImageService.list(newQueryWrapper);
            ElevatorVo elevatorVo = BeanUtil.copyProperties(elevator1, ElevatorVo.class);
            elevatorVo.setElevatorImageList(elevatorImageList);

        }
        selectResult.setRecords(elevatorList);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询电梯分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param elevator 实例对象
     * @return 实例对象
     */
    public Result insert(Elevator elevator, MultipartFile[] files) {
        Manufacturer manufacturer = manufacturerService.getById(elevator.getManufacturerId());
        Location location = locationService.getById(elevator.getLocationId());
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = manufacturer.getPrefix() + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<Elevator> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("elevator_number", number);
            if (getOne(queryWrapper) != null)
                continue;
            else
                break;
        }
        elevator.setElevatorNumber(number);
        elevator.setLocationName(location.getLocationName());
        elevator.setAddress(location.getAddress());
        elevator.setContactPerson(location.getContactPerson());
        elevator.setContactNumber(location.getContactNumber());
        elevator.setManufacturerName(manufacturer.getManufacturerName());
        elevator.setAddress(location.getAddress());
        if (save(elevator) == true) {
            if (insertElevatorImage(elevator.getId(), files).getCode() == 2000) {
                Message message = new Message();
                message.setMessage("添加新电梯  " + elevator.getElevatorNumber() + ":" + elevator.getElevatorName());
                messageService.save(message);
                return new Result(true, StatusCode.OK, "添加电梯成功");
            } else
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
        } else
            return new Result(false, StatusCode.ERROR, "添加电梯失败");
    }

    /**
     * 更新数据
     *
     * @param elevator 实例对象
     * @return 实例对象
     */
    public Result update(Elevator elevator, MultipartFile[] files) {
        if (updateById(elevator) == true) {
            if (insertElevatorImage(elevator.getId(), files).getCode() == 2000) {
                Message message = new Message();
                message.setMessage("更新电梯内容  " + elevator.getElevatorNumber() + ":" + elevator.getElevatorName());
                messageService.save(message);
                return new Result(true, StatusCode.OK, "更新电梯数据成功");
            } else
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
        } else
            return new Result(false, StatusCode.ERROR, "更新电梯数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("elevator_id", id);
        List<ElevatorImage> elevatorImageList = elevatorImageService.list(queryWrapper);
        if (elevatorImageList.isEmpty()) {
            for (ElevatorImage elevatorImage : elevatorImageList) {
                if (elevatorImageService.removeById(elevatorImage.getId()) == false)
                    return new Result(false, StatusCode.ERROR, "删除电梯图片失败");
            }
        }
        Elevator elevator = getById(id);
        if (removeById(id) == true) {
            Message message = new Message();
            message.setMessage("删除电梯  " + elevator.getElevatorNumber() + ":" + elevator.getElevatorName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "删除电梯成功");
        } else
            return new Result(false, StatusCode.ERROR, "删除电梯失败");
    }

    /**
     * 添加图片
     *
     * @param files 图片
     * @return 是否成功
     */
    public Result insertElevatorImage(Integer id, MultipartFile[] files) {
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
            ElevatorImage elevatorImage = new ElevatorImage();
            elevatorImage.setElevatorId(id);
            elevatorImage.setImageName(destName);
            elevatorImage.setImagePosition("/elevator/" + destName);
            if (elevatorImageService.save(elevatorImage) == false)
                return new Result(false, StatusCode.ERROR, "电梯图片上传失败");
        }
        return new Result(true, StatusCode.OK, "图片上传成功");
    }

}
