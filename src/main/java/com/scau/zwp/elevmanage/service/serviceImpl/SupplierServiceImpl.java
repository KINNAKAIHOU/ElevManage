package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Location;
import com.scau.zwp.elevmanage.entity.Message;
import com.scau.zwp.elevmanage.entity.Supplier;
import com.scau.zwp.elevmanage.mapper.SupplierMapper;
import com.scau.zwp.elevmanage.service.MessageService;
import com.scau.zwp.elevmanage.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 供货商 服务实现类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Resource
    private MessageService messageService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Result queryById(Integer id) {
        System.out.println(id);
        Supplier supplier = getById(id);
        if (supplier == null)
            return new Result(false, StatusCode.ERROR, "通过ID查询供货商信息失败");
        else
            return new Result(true, StatusCode.OK, "通过ID查询供货商信息成功", supplier);
    }

    /**
     * 分页查询
     *
     * @param supplier 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Result paginQuery(Supplier supplier, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(supplier.getSupplierName())) {
            queryWrapper.like(Supplier::getSupplierName, supplier.getSupplierName());
        }
        if (StrUtil.isNotBlank(supplier.getContactPerson()) || StrUtil.isNotBlank(supplier.getAddress())) {
            queryWrapper.and(wrapper -> wrapper
                    .like(Supplier::getContactPerson, supplier.getContactPerson())
                    .or()
                    .like(Supplier::getAddress, supplier.getAddress()));
        }
        //2. 执行分页查询
        Page<Supplier> pagin = new Page<>(current, size, true);
        IPage<Supplier> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return new Result(true, StatusCode.OK, "查询供货商分页成功", pagin);
    }

    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    public Result insert(Supplier supplier) {
        if (save(supplier) == true) {
            Message message = new Message();
            message.setMessage("添加新供货商  " + supplier.getSupplierName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "添加供货商成功");
        } else
            return new Result(false, StatusCode.ERROR, "添加供货商失败");
    }

    /**
     * 更新数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    public Result update(Supplier supplier) {
        if (updateById(supplier) == true) {
            Message message = new Message();
            message.setMessage("更新供货商内容  " + supplier.getSupplierName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "更新供货商数据成功");
        } else
            return new Result(false, StatusCode.ERROR, "更新供货商数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Result deleteById(Integer id) {
        Supplier supplier = getById(id);
        if (removeById(id) == true) {
            Message message = new Message();
            message.setMessage("删除供货商  " + supplier.getSupplierName());
            messageService.save(message);
            return new Result(true, StatusCode.OK, "删除供货商成功");
        } else
            return new Result(false, StatusCode.ERROR, "删除供货商失败");
    }

}
