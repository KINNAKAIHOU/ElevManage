package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-02-19 10:47:51
 */
@Getter
@Setter
@TableName("em_inventory")
@ApiModel(value = "Inventory对象", description = "库存管理")
public class Inventory {

    @ApiModelProperty("库存管理ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("配件ID")
    @TableField("accessory_id")
    private Integer accessoryId;

    @ApiModelProperty("配件编号")
    @TableField("accessory_number")
    private String accessoryNumber;

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
