package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Inventory;
import com.scau.zwp.elevmanage.mapper.InventoryMapper;
import com.scau.zwp.elevmanage.service.InventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 库存管理 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public R<Inventory> queryById(Integer id) {
        System.out.println(id);
        Inventory inventory = getById(id);
        if (inventory == null)
            return R.error("通过ID查询配件信息失败");
        else
            return R.success(inventory);
    }


    /**
     * 分页查询
     *
     * @param inventory 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    public Page<Inventory> paginQuery(Inventory inventory, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(inventory.getAccessoryNumber())) {
            queryWrapper.like(Inventory::getAccessoryNumber, inventory.getAccessoryNumber());
        }
        if (StrUtil.isNotBlank(inventory.getAccessoryName())) {
            queryWrapper.like(Inventory::getAccessoryName, inventory.getAccessoryName());
        }
        if (StrUtil.isNotBlank(inventory.getSpecification())) {
            queryWrapper.like(Inventory::getSpecification, inventory.getSpecification());
        }
        if (StrUtil.isNotBlank(inventory.getType())) {
            queryWrapper.like(Inventory::getType, inventory.getType());
        }
        if (StrUtil.isNotBlank(inventory.getUnit())) {
            queryWrapper.like(Inventory::getUnit, inventory.getUnit());
        }
        //2. 执行分页查询
        Page<Inventory> pagin = new Page<>(current, size, true);
        IPage<Inventory> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }


    /**
     * 更新数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(Inventory inventory) {
        if (updateById(inventory) == true) {
            if (inventory.getQuantity() < inventory.getWarningQuantity())
                return R.warring(inventory.getAccessoryId() + ":" + inventory.getAccessoryName() + "库存不足");
            return R.success(true);
        } else
            return R.error("更新数据失败");
    }


    /**
     * 增加数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    public R<Boolean> increase(Integer accessoryId, Integer size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("accessory_id", accessoryId);
        Inventory inventory = getOne(queryWrapper);
        inventory.setQuantity(inventory.getQuantity() + size);
        if (updateById(inventory) == true) {
            if (inventory.getQuantity() < inventory.getWarningQuantity())
                return R.warring(inventory.getAccessoryId() + ":" + inventory.getAccessoryName() + "库存不足");
            return R.success(true);
        } else
            return R.error("更新数据失败");

    }


    /**
     * 减少数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    public R<Boolean> reduce(Integer accessoryId, Integer size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("accessory_id", accessoryId);
        Inventory inventory = getOne(queryWrapper);
        inventory.setQuantity(inventory.getQuantity() - size);
        if (inventory.getQuantity() < 0)
            inventory.setQuantity(0);
        if (updateById(inventory) == true) {
            if (inventory.getQuantity() < inventory.getWarningQuantity())
                return R.warring(inventory.getAccessoryId() + ":" + inventory.getAccessoryName() + "库存不足");
            return R.success(true);
        } else
            return R.error("更新数据失败");
    }


}
