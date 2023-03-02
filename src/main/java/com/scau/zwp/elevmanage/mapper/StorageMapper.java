package com.scau.zwp.elevmanage.mapper;

import com.scau.zwp.elevmanage.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * <p>
 * 配件入库 Mapper 接口
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    @Select("select sum(total_price) from em_storage where storage_time between #{start} and #{end}")
    Double selectTotalPriceBetween(@Param("start") Date start, @Param("end") Date end);

}
