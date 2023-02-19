package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.service.ElevatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电梯 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Service
public class ElevatorServiceImpl extends ServiceImpl<ElevatorMapper, Elevator> implements ElevatorService {

    @Mapper
    private ElevatorMapper elevatorMapper;

    /**
     * 分页查询
     *
     * @param elevator 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Page<Elevator> paginQuery(Elevator elevator, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Elevator> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(elevator.getElevatorNumber())) {
            queryWrapper.like(Elevator::getElevatorNumber, elevator.getElevatorNumber());
        }
        if (StrUtil.isNotBlank(elevator.getElevatorName())) {
            queryWrapper.like(Elevator::getElevatorName, elevator.getElevatorName());
        }
        if (StrUtil.isNotBlank(elevator.getLocationName())) {
            queryWrapper.like(Elevator::getLocationName, elevator.getLocationName());
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
        if (StrUtil.isNotBlank(elevator.getAddress())) {
            queryWrapper.like(Elevator::getAddress, elevator.getAddress());
        }
        if (StrUtil.isNotBlank(elevator.getManufacturerId())) {
            queryWrapper.like(Elevator::getManufacturerId, elevator.getManufacturerId());
        }
        if (StrUtil.isNotBlank(elevator.getLocationId())) {
            queryWrapper.like(Elevator::getLocationId, elevator.getLocationId());
        }
        if (StrUtil.isNotBlank(elevator.getRemarks())) {
            queryWrapper.like(Elevator::getRemarks, elevator.getRemarks());
        }
        if (StrUtil.isNotBlank(elevator.getIsDeleted())) {
            queryWrapper.like(Elevator::getIsDeleted, elevator.getIsDeleted());
        }
        //2. 执行分页查询
        Page<Elevator> pagin = new Page<>(current, size, true);
        IPage<Elevator> selectResult = elevatorMapper.selectPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }
}
