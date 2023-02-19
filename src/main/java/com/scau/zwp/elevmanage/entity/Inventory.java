package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 库存管理
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Getter
@Setter
@TableName("em_inventory")
@ApiModel(value = "Inventory对象", description = "库存管理")
public class Inventory {

    @ApiModelProperty("库存管理ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("配件ID")
    @TableField("accessory_id")
    private String accessoryId;

    @ApiModelProperty("库存数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("预警数量")
    @TableField("warning_quantity")
    private Integer warningQuantity;

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
