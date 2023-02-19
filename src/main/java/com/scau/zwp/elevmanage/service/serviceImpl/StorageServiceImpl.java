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
import com.scau.zwp.elevmanage.entity.Storage;
import com.scau.zwp.elevmanage.mapper.StorageMapper;
import com.scau.zwp.elevmanage.service.InspectionService;
import com.scau.zwp.elevmanage.service.InventoryService;
import com.scau.zwp.elevmanage.service.StorageItemService;
import com.scau.zwp.elevmanage.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.vo.ElevatorVo;
import com.scau.zwp.elevmanage.vo.StorageVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 配件入库 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Resource
    private StorageItemService storageItemService;
    @Resource
    private InventoryService inventoryService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public R<StorageVo> queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("storage_id", id);
        List<StorageItem> storageItemList = storageItemService.list(queryWrapper);
        Storage storage = getById(id);
        if (storage == null)
            return R.error("通过ID查询配件入库信息失败");
        else{
            StorageVo storageVo = BeanUtil.copyProperties(storage, StorageVo.class);
            storageVo.setStorageItemList(storageItemList);
            return R.success(storageVo);
        }
    }


    /**
     * 分页查询
     *
     * @param storage 筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    public Page<Storage> paginQuery(Storage storage, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Storage> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(storage.getStorageNumber())) {
            queryWrapper.like(Storage::getStorageNumber, storage.getStorageNumber());
        }
        if (StrUtil.isNotBlank(storage.getOperatorPerson())) {
            queryWrapper.like(Storage::getOperatorPerson, storage.getOperatorPerson());
        }
        if (StrUtil.isNotBlank(storage.getSupplierName())) {
            queryWrapper.like(Storage::getSupplierName, storage.getSupplierName());
        }
        if (StrUtil.isNotBlank(storage.getContactPerson())) {
            queryWrapper.like(Storage::getContactPerson, storage.getContactPerson());
        }
        if (StrUtil.isNotBlank(storage.getAddress())) {
            queryWrapper.like(Storage::getAddress, storage.getAddress());
        }
        //2. 执行分页查询
        Page<Storage> pagin = new Page<>(current, size, true);
        IPage<Storage> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param storage 实例对象
     * @return 实例对象
     */
    public R<Boolean> insert(Storage storage, List<StorageItem> storageItems) {
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String prefix = "RK" + date;
        int num = 3;//编号的位数
        String number = "";
        for (int i = 1; i <= 100; i++) {//要输出的编号个数为100个，从001........100
            QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
            number = prefix + String.format("%0" + num + "d", i);//格式化字符串，把i格式化成num位的字符串，不足的位补0;例：String.format("%05d",123);结果为“00123”
            queryWrapper.eq("storage_number", number);
            if (getOne(queryWrapper) != null)
                continue;
            else
                break;
        }
        storage.setStorageNumber(number);
        if (save(storage) == true) {
            if (storageItems != null) {
                for (StorageItem storageItem : storageItems) {
                    storageItem.setStorageId(storage.getId());
                    if (storageItemService.save(storageItem) == true) {
                        if (inventoryService.increase(storageItem.getAccessoryId(), storageItem.getQuantity()).getCode() != 1)
                            return R.error("库存登记失败");
                    } else
                        return R.error("入库详情登记失败");
                }
            }
            return R.success(true);
        } else
            return R.error("入库失败");
    }


    /**
     * 更新数据
     *
     * @param storage 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(Storage storage) {
        if (updateById(storage) == true) {
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
        if (removeById(id) == true) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("storage_id", id);
            List<StorageItem> storageItemList = storageItemService.list(queryWrapper);
            if (storageItemList != null) {
                for (StorageItem storageItem : storageItemList) {
                    if (storageItemService.removeById(storageItem) == true) {
                        if (inventoryService.reduce(storageItem.getAccessoryId(), storageItem.getQuantity()).getCode() != 1)
                            return R.error("库存登记失败");
                    } else
                        return R.error("入库详情登记失败");
                }
            }
            return R.success(true);
        } else
            return R.error("通过主键删除数据失败");
    }
}
