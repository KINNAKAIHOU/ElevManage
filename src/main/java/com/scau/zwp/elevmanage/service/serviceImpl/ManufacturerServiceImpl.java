package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Manufacturer;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.mapper.ManufacturerMapper;
import com.scau.zwp.elevmanage.service.ManufacturerService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 电梯厂家 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class ManufacturerServiceImpl extends ServiceImpl<ManufacturerMapper, Manufacturer> implements ManufacturerService {
    @Resource
    private ManufacturerMapper manufacturerMapper;
    @Resource
    private ElevatorMapper elevatorMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        System.out.println(id);
        Manufacturer manufacturer = getById(id);
        if (manufacturer == null)
            return new Result(false, StatusCode.ERROR, "通过ID查电梯厂家所信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询电梯厂家信息成功", manufacturer);
    }

    /**
     * 分页查询
     *
     * @param manufacturer 筛选条件
     * @param current      当前页码
     * @param size         每页大小
     * @return
     */
    public Result paginQuery(Manufacturer manufacturer, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Manufacturer> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(manufacturer.getManufacturerName())){
            queryWrapper.like(Manufacturer::getManufacturerName, manufacturer.getManufacturerName());
        }
        if(StrUtil.isNotBlank(manufacturer.getContactPerson())){
            queryWrapper.like(Manufacturer::getContactPerson, manufacturer.getContactPerson());
        }
        if(StrUtil.isNotBlank(manufacturer.getPrefix())){
            queryWrapper.like(Manufacturer::getPrefix, manufacturer.getPrefix());
        }
        if(StrUtil.isNotBlank(manufacturer.getAddress())){
            queryWrapper.like(Manufacturer::getAddress, manufacturer.getAddress());
        }
        //2. 执行分页查询
        Page<Manufacturer> pagin = new Page<>(current, size, true);
        IPage<Manufacturer> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询电梯厂家分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param manufacturer 实例对象
     * @return 实例对象
     */
    public Result insert(Manufacturer manufacturer) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("prefix", manufacturer.getPrefix());
        if (getOne(queryWrapper) != null) {
            return new Result(false, StatusCode.ERROR, "电梯厂家编号重复");
        } else {
            if (save(manufacturer) == true)
                return new Result(true, StatusCode.OK, "添加电梯厂家");
            else
                return new Result(false, StatusCode.ERROR, "添加电梯厂家失败");
        }

    }

    /**
     * 更新数据
     *
     * @param manufacturer 实例对象
     * @return 实例对象
     */
    public Result update(Manufacturer manufacturer) {
        if (updateById(manufacturer) == true) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("manufacturer_id", manufacturer.getId());
            List<Elevator> elevatorList = elevatorMapper.selectList(queryWrapper);
            if (elevatorList != null) {
                for (Elevator elevator : elevatorList) {
                    elevator.setManufacturerName(manufacturer.getManufacturerName());
                    if (elevatorMapper.updateById(elevator) == 0)
                        return new Result(false, StatusCode.ERROR, "电梯信息更新失败");
                }
            }
            return new Result(true, StatusCode.OK, "更新电梯厂家数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新电梯厂家数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        if (removeById(id) == true)
            return new Result(true, StatusCode.OK, "删除电梯厂家成功");
        else
            return new Result(false, StatusCode.ERROR, "删除电梯厂家失败");
    }

}
