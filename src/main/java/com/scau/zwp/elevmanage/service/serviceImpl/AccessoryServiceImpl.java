package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Accessory;
import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.Accessory;
import com.scau.zwp.elevmanage.mapper.AccessoryMapper;
import com.scau.zwp.elevmanage.service.AccessoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 配件 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:50
 */
@Service
public class AccessoryServiceImpl extends ServiceImpl<AccessoryMapper, Accessory> implements AccessoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public R<Accessory> queryById(Integer id) {
        System.out.println(id);
        Accessory accessory = getById(id);
        if (accessory == null)
            return R.error("通过ID查询配件信息失败");
        else
            return R.success(accessory);
    }

    /**
     * 分页查询
     *
     * @param accessory 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    public Page<Accessory> paginQuery(Accessory accessory, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Accessory> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(accessory.getAccessoryNumber())) {
            queryWrapper.like(Accessory::getAccessoryNumber, accessory.getAccessoryNumber());
        }
        if (StrUtil.isNotBlank(accessory.getAccessoryName())) {
            queryWrapper.like(Accessory::getAccessoryName, accessory.getAccessoryName());
        }
        if (StrUtil.isNotBlank(accessory.getSpecification())) {
            queryWrapper.like(Accessory::getSpecification, accessory.getSpecification());
        }
        if (StrUtil.isNotBlank(accessory.getType())) {
            queryWrapper.like(Accessory::getType, accessory.getType());
        }
        if (StrUtil.isNotBlank(accessory.getUnit())) {
            queryWrapper.like(Accessory::getUnit, accessory.getUnit());
        }
        //2. 执行分页查询
        Page<Accessory> pagin = new Page<>(current, size, true);
        IPage<Accessory> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param accessory 实例对象
     * @return 实例对象
     */
    public R<Boolean> insert(Accessory accessory) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("accessory_number", accessory.getAccessoryNumber());
        if (getOne(queryWrapper) == null) {
            if (save(accessory) == true)
                return R.success(true);
            else
                return R.error("新增数据失败");
        } else
            return R.error("配件编码重复");
    }

    /**
     * 更新数据
     *
     * @param accessory 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(Accessory accessory) {
        if (updateById(accessory) == true) {
            return R.success(true);
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
        if (removeById(id) == true)
            return R.success(true);
        else
            return R.error("通过主键删除数据失败");
    }

}
