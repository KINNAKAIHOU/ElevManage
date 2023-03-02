package com.scau.zwp.elevmanage.mapper;

import com.scau.zwp.elevmanage.entity.Maintenance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

/**
 * <p>
 * 维修报告 Mapper 接口
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Mapper
public interface MaintenanceMapper extends BaseMapper<Maintenance> {

    @Select("select sum(total_price) from em_maintenance where maintenance_data between #{start} and #{end}")
    Double selectTotalPriceBetween(@Param("start") Date start, @Param("end") Date end);

}
