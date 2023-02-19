package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.mapper.LocationMapper;
import com.scau.zwp.elevmanage.service.LocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

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
    @Mapper
    private LocationMapper locationMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public R<Location> queryById(Integer id) {
        System.out.println(id);
        Location location = getById(id);
        if (location == null)
            return R.error("通过ID查询电梯厂家信息失败");
        else
            return R.success(location);
    }

    /**
     * 分页查询
     *
     * @param location 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Page<Location> paginQuery(Location location, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Location> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(location.getLocationName())) {
            queryWrapper.like(Location::getLocationName, location.getLocationName());
        }
        if (StrUtil.isNotBlank(location.getContactPerson())) {
            queryWrapper.like(Location::getContactPerson, location.getContactPerson());
        }
        if (StrUtil.isNotBlank(location.getAddress())) {
            queryWrapper.like(Location::getAddress, location.getAddress());
        }
        //2. 执行分页查询
        Page<Location> pagin = new Page<>(current, size, true);
        IPage<Location> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    public R<Boolean> insert(Location location) {
        if (save(location) == true)
            return R.success(true);
        else
            return R.error("新增数据失败");
    }

    /**
     * 更新数据
     *
     * @param location 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(Location location) {
        if (updateById(location) == true)
            return R.success(true);
        else
            return R.error("更新数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public R<Boolean> deleteById(Integer id) {
        if (removeById(id) == true)
            return R.success(true);
        else
            return R.error("通过主键删除数据失败");
    }

}
