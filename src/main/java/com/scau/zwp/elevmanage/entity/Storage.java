package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 配件入库
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Getter
@Setter
@TableName("em_storage")
@ApiModel(value = "Storage对象", description = "配件入库")
public class Storage {

    @ApiModelProperty("配件入库ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("入库编号")
    @TableField("storage_number")
    private String storageNumber;

    @ApiModelProperty("入库日期")
    @TableField("storage_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime storageTime;

    @ApiModelProperty("经办人")
    @TableField("operator_person")
    private String operatorPerson;

    @ApiModelProperty("供货商名称")
    @TableField("supplier_name")
    private String supplierName;

    @ApiModelProperty("联系人")
    @TableField("contact_person")
    private String contactPerson;

    @ApiModelProperty("联系电话")
    @TableField("contact_number")
    private Integer contactNumber;

    @ApiModelProperty("场所地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("总金额")
    @TableField("total_price")
    private BigDecimal totalPrice;

    @ApiModelProperty("供货商ID")
    @TableField("supplier_id")
    private Integer supplierId;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    @TableLogic
    private String isDeleted;
}
