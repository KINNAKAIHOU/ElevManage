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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 电梯
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Data
@NoArgsConstructor
@TableName("em_elevator")
@ApiModel(value = "Elevator对象", description = "电梯")
public class Elevator {

    @ApiModelProperty("电梯id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("电梯编号")
    @TableField("elevator_number")
    private String elevatorNumber;

    @ApiModelProperty("电梯名称")
    @TableField("elevator_name")
    private String elevatorName;

    @ApiModelProperty("场所名称")
    @TableField("location_name")
    private String locationName;

    @ApiModelProperty("生产厂家")
    @TableField("manufacturer_name")
    private String manufacturerName;

    @ApiModelProperty("设备型号")
    @TableField("model")
    private String model;

    @ApiModelProperty("载重速度")
    @TableField("load_speed")
    private Integer loadSpeed;

    @ApiModelProperty("产品编号")
    @TableField("product_number")
    private String productNumber;

    @ApiModelProperty("详细地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("电梯厂家ID")
    @TableField("manufacturer_id")
    private Integer manufacturerId;

    @ApiModelProperty("场所ID")
    @TableField("location_id")
    private Integer locationId;

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
