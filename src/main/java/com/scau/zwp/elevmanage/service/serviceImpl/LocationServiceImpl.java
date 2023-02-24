package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.mapper.ElevatorMapper;
import com.scau.zwp.elevmanage.mapper.LocationMapper;
import com.scau.zwp.elevmanage.service.LocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 场所 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements LocationService {
    @Resource
    private LocationMapper locationMapper;
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
        Location location = getById(id);
        if (location == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询场所信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询场所信息成功", location);
    }

    /**
     * 分页查询
     *
     * @param location 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Result paginQuery(Location location, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Location> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(location.getLocationName())){
            queryWrapper.like(Location::getLocationName, location.getLocationName());
        }
        if(StrUtil.isNotBlank(location.getContactPerson())){
            queryWrapper.like(Location::getContactPerson, location.getContactPerson());
        }
        if(StrUtil.isNotBlank(location.getAddress())){
            queryWrapper.like(Location::getAddress, location.getAddress());
        }
        //2. 执行分页查询
        Page<Location> pagin = new Page<>(current, size, true);
        IPage<Location> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询场所分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    public Result insert(Location location) {
        if (save(location) == true)
            return new Result(true, StatusCode.OK, "添加场所成功");
        else
            return new Result(false, StatusCode.ERROR, "添加场所失败");
    }

    /**
     * 更新数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    public Result update(Location location) {
        if (updateById(location) == true) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("location_id", location.getId());
            List<Elevator> elevatorList = elevatorMapper.selectList(queryWrapper);
            if (elevatorList != null) {
                for (Elevator elevator : elevatorList) {
                    elevator.setLocationName(location.getLocationName());
                    elevator.setAddress(location.getAddress());
                    elevator.setContactPerson(location.getContactPerson());
                    elevator.setContactNumber(location.getContactNumber());
                    if (elevatorMapper.updateById(elevator) == 0)
                        return new Result(false, StatusCode.ERROR, "电梯信息更新失败");
                }
            }
            return new Result(true, StatusCode.OK, "更新场所数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新场所数据失败");
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        if (removeById(id) == true)
            return new Result(true, StatusCode.OK, "删除场所成功");
        else
            return new Result(false, StatusCode.ERROR, "删除场所失败");
    }

}
