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
import com.scau.zwp.elevmanage.entity.Storage;
import com.scau.zwp.elevmanage.mapper.AccessoryMapper;
import com.scau.zwp.elevmanage.mapper.StorageMapper;
import com.scau.zwp.elevmanage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.zwp.elevmanage.vo.ElevatorVo;
import com.scau.zwp.elevmanage.vo.StorageVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Resource
    private SupplierService supplierService;
    @Resource
    private MessageService messageService;
    @Resource
    private AccessoryMapper accessoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("storage_id", id);
        List<StorageItem> storageItemList = storageItemService.list(queryWrapper);
        Storage storage = getById(id);
        if (storage == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询配件入库信息失败");
        else {
            StorageVo storageVo = BeanUtil.copyProperties(storage, StorageVo.class);
            storageVo.setStorageItemList(storageItemList);
            return new Result(true, StatusCode.OK, "通过ID查询配件入库信息成功", storageVo);
        }
    }


    /**
     * 分页查询
     *
     * @param storage   筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @param startTime 开启日期
     * @param endTime   结束日期
     * @return
     */
    public Result paginQuery(Storage storage, Integer current, Integer size, String startTime, String endTime) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Storage> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(storage.getOperatorPerson())) {
            queryWrapper.like(Storage::getOperatorPerson, storage.getOperatorPerson());
        }
        if (storage.getSupplierId()!=null) {
            queryWrapper.like(Storage::getSupplierId, storage.getSupplierId());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (startTime != null) {
            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
            queryWrapper.ge(Storage::getStorageTime, startDateTime);
        }
        if (endTime != null) {
            LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
            queryWrapper.le(Storage::getStorageTime, endDateTime);
        }
        //2. 执行分页查询
        Page<Storage> pagin = new Page<>(current, size, true);
        IPage<Storage> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询配件入库分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param storage 实例对象
     * @return 实例对象
     */
    public Result insert(Storage storage, List<StorageItem> storageItems) {
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
        Supplier supplier = supplierService.getById(storage.getSupplierId());
        storage.setSupplierName(supplier.getSupplierName());
        storage.setContactPerson(supplier.getContactPerson());
        storage.setContactNumber(supplier.getContactNumber());
        storage.setAddress(supplier.getAddress());
        if (save(storage) == true) {
            if (storageItems != null) {
                for (StorageItem storageItem : storageItems) {
                    Accessory accessory = accessoryMapper.selectById(storageItem.getAccessoryId());
                    storageItem.setStorageId(storage.getId());
                    storageItem.setAccessoryNumber(accessory.getAccessoryNumber());
                    storageItem.setAccessoryName(accessory.getAccessoryName());
                    storageItem.setSpecification(accessory.getSpecification());
                    storageItem.setType(accessory.getType());
                    storageItem.setUnit(accessory.getUnit());
                    if (storageItemService.save(storageItem) == true) {
                        if (inventoryService.increase(storageItem.getAccessoryId(), storageItem.getQuantity()).getCode() == 2001)
                            return new Result(false, StatusCode.ERROR, "库存登记失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "配件入库详情登记失败");
                }
            }
            Message message = new Message();
            message.setMessage("添加新入库消息  " + storage.getStorageNumber() + "--" + storage.getSupplierName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "配件入库登记成功");
        } else
            return new Result(false, StatusCode.ERROR, "配件入库登记失败");
    }


    /**
     * 更新数据
     *
     * @param storage 实例对象
     * @return 实例对象
     */
    public Result update(Storage storage) {
        if (updateById(storage) == true) {
            Message message = new Message();
            message.setMessage("更新入库消息内容  " + storage.getStorageNumber() + "--" + storage.getSupplierName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "更新配件入库数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新配件入库数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        if (removeById(id) == true) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("storage_id", id);
            List<StorageItem> storageItemList = storageItemService.list(queryWrapper);
            if (storageItemList != null) {
                for (StorageItem storageItem : storageItemList) {
                    if (storageItemService.removeById(storageItem) == true) {
                        if (inventoryService.reduce(storageItem.getAccessoryId(), storageItem.getQuantity()).getCode() == 2001)
                            return new Result(false, StatusCode.ERROR, "删除库存登记失败");
                    } else
                        return new Result(false, StatusCode.ERROR, "删除配件入库详情登记失败");
                }
            }
            Storage storage = getById(id);
            Message message = new Message();
            message.setMessage("删除入库消息  " + storage.getStorageNumber() + "--" + storage.getSupplierName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "删除配件入库成功");
        } else
            return new Result(false, StatusCode.ERROR, "删除配件入库失败");
    }
}
