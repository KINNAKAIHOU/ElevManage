package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 维修报告详情
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Getter
@Setter
@TableName("em_maintenance_item")
@ApiModel(value = "MaintenanceItem对象", description = "维修报告详情")
public class MaintenanceItem {

    @ApiModelProperty("维修报告详情ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("维修报告ID")
    @TableField("maintenance_id")
    private String maintenanceId;

    @ApiModelProperty("配件名称")
    @TableField("accessory_name")
    private String accessoryName;

    @ApiModelProperty("规格型号")
    @TableField("specification")
    private String specification;

    @ApiModelProperty("配件类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("进货价")
    @TableField("purchase_price")
    private BigDecimal purchasePrice;

    @ApiModelProperty("金额")
    @TableField("total_price")
    private BigDecimal totalPrice;

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
