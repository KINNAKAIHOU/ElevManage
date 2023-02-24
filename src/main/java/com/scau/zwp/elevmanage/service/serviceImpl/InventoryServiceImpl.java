package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Inventory;
import com.scau.zwp.elevmanage.mapper.InventoryMapper;
import com.scau.zwp.elevmanage.service.InventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Result queryById(Integer id) {
        System.out.println(id);
        Inventory inventory = getById(id);
        if (inventory == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询库存管理信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询库存管理信息成功", inventory);
    }


    /**
     * 分页查询
     *
     * @param inventory 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    public Result paginQuery(Inventory inventory, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(inventory.getAccessoryNumber())){
            queryWrapper.like(Inventory::getAccessoryNumber, inventory.getAccessoryNumber());
        }
        if(StrUtil.isNotBlank(inventory.getAccessoryName())){
            queryWrapper.like(Inventory::getAccessoryName, inventory.getAccessoryName());
        }
        if(StrUtil.isNotBlank(inventory.getSpecification())){
            queryWrapper.like(Inventory::getSpecification, inventory.getSpecification());
        }
        if(StrUtil.isNotBlank(inventory.getType())){
            queryWrapper.like(Inventory::getType, inventory.getType());
        }
        if(StrUtil.isNotBlank(inventory.getUnit())){
            queryWrapper.like(Inventory::getUnit, inventory.getUnit());
        }
        //2. 执行分页查询
        Page<Inventory> pagin = new Page<>(current, size, true);
        IPage<Inventory> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询库存管理分页成功", pagin);
    }


    /**
     * 更新数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    public Result update(Inventory inventory) {
        if (updateById(inventory) == true) {
            return new Result(true, StatusCode.OK, "更新库存管理数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新库存管理数据失败");
    }


    /**
     * 增加数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    public Result increase(Integer accessoryId, Integer size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("accessory_id", accessoryId);
        Inventory inventory = getOne(queryWrapper);
        inventory.setQuantity(inventory.getQuantity() + size);
        if (updateById(inventory) == true) {
            return new Result(true, StatusCode.OK, "增加数量成功");
        } else
            return new Result(false, StatusCode.ERROR, "增加数量失败");

    }


    /**
     * 减少数量
     *
     * @param accessoryId 配件ID
     * @param size        数量
     * @return 实例对象
     */
    public Result reduce(Integer accessoryId, Integer size) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("accessory_id", accessoryId);
        Inventory inventory = getOne(queryWrapper);
        inventory.setQuantity(inventory.getQuantity() - size);
        if (inventory.getQuantity() < 0)
            inventory.setQuantity(0);
        if (updateById(inventory) == true) {
            return new Result(true, StatusCode.OK, "减少数量成功");
        } else
            return new Result(false, StatusCode.ERROR, "减少数量失败");
    }

    /**
     * 检查所有库存
     *
     * @return 实例对象
     */
    public Result checkAllInventory() {
        List<Inventory> inventoryList = list();
        if (inventoryList != null) {
            List<Inventory> warringInventoryList = new ArrayList<>();
            for (Inventory inventory : inventoryList) {
                if (inventory.getQuantity() < inventory.getWarningQuantity())
                    warringInventoryList.add(inventory);
            }
            if (!warringInventoryList.isEmpty()) {
                return new Result(true, StatusCode.OK, "检查所有库存成功,有配件数量低于预警", warringInventoryList);
            } else
                return new Result(true, StatusCode.OK, "检查所有库存成功,没有配件数量低于预警");
        } else
            return new Result(true, StatusCode.OK, "检查所有库存成功,没有配件数量低于预警");
    }
}
