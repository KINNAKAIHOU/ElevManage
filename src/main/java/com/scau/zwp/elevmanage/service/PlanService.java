package com.scau.zwp.elevmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Plan;
import com.scau.zwp.elevmanage.entity.Plan;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * <p>
 * 检查计划 服务类
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
public interface PlanService extends IService<Plan> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R<Plan> queryById(Integer id);

    /**
     * 分页查询
     *
     * @param plan    筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    Page<Plan> paginQuery(Plan plan, Integer current, Integer size);

    /**
     * 新增数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    R<Boolean> insert(Plan plan);

    /**
     * 更新数据
     *
     * @param plan 实例对象
     * @return 实例对象
     */
    R<Boolean> update(Plan plan);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R<Boolean> deleteById(Integer id);

    /**
     * 检查所有计划
     *
     * @return 是否成功
     */
    R<Boolean> checkAllPlan();

}
