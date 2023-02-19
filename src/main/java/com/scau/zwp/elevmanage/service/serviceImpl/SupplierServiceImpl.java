package com.scau.zwp.elevmanage.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Supplier;
import com.scau.zwp.elevmanage.entity.Supplier;
import com.scau.zwp.elevmanage.mapper.SupplierMapper;
import com.scau.zwp.elevmanage.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public R<Supplier> queryById(Integer id) {
        System.out.println(id);
        Supplier supplier = getById(id);
        if (supplier == null)
            return R.error("通过ID查询供货商信息失败");
        else
            return R.success(supplier);
    }

    /**
     * 分页查询
     *
     * @param supplier 筛选条件
     * @param current  当前页码
     * @param size     每页大小
     * @return
     */
    public Page<Supplier> paginQuery(Supplier supplier, Integer current, Integer size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(supplier.getSupplierName())) {
            queryWrapper.like(Supplier::getSupplierName, supplier.getSupplierName());
        }
        if (StrUtil.isNotBlank(supplier.getContactPerson())) {
            queryWrapper.like(Supplier::getContactPerson, supplier.getContactPerson());
        }
        if (StrUtil.isNotBlank(supplier.getAddress())) {
            queryWrapper.like(Supplier::getAddress, supplier.getAddress());
        }
        //2. 执行分页查询
        Page<Supplier> pagin = new Page<>(current, size, true);
        IPage<Supplier> selectResult = page(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    public R<Boolean> insert(Supplier supplier) {
        if (save(supplier) == true)
            return R.success(true);
        else
            return R.error("新增数据失败");
    }

    /**
     * 更新数据
     *
     * @param supplier 实例对象
     * @return 实例对象
     */
    public R<Boolean> update(Supplier supplier) {
        if (updateById(supplier) == true) {
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
