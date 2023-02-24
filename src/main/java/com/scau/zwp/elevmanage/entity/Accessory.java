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
 * 配件
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:50
 */
@Getter
@Setter
@TableName("em_accessory")
@ApiModel(value = "Accessory对象", description = "配件")
public class Accessory {

    @ApiModelProperty("配件ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
