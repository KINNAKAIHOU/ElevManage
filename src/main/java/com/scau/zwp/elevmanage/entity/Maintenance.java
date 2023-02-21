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
 * 维修报告
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Getter
@Setter
@TableName("em_maintenance")
@ApiModel(value = "Maintenance对象", description = "维修报告")
public class Maintenance {

    @ApiModelProperty("维修报告ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("维修编号")
    @TableField("maintenance_number")
    private String maintenanceNumber;

    @ApiModelProperty("电梯编号")
    @TableField("elevator_number")
    private String elevatorNumber;

    @ApiModelProperty("电梯地点")
    @TableField("location_name")
    private String locationName;

    @ApiModelProperty("详细地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("联系人")
    @TableField("contact_person")
    private String contactPerson;

    @ApiModelProperty("联系电话")
    @TableField("contact_number")
    private Integer contactNumber;

    @ApiModelProperty("检查描述")
    @TableField("check_description")
    private String checkDescription;

    @ApiModelProperty("维修工费")
    @TableField("maintenance_price")
    private BigDecimal maintenancePrice;

    @ApiModelProperty("配件费用")
    @TableField("accessory_price")
    private BigDecimal accessoryPrice;

    @ApiModelProperty("总费用")
    @TableField("total_price")
    private BigDecimal totalPrice;

    @ApiModelProperty("故障描述")
    @TableField("fault_description")
    private String faultDescription;

    @ApiModelProperty("维修日期")
    @TableField("maintenance_data")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maintenanceData;

    @ApiModelProperty("维修人")
    @TableField("maintenance_person")
    private String maintenancePerson;

    @ApiModelProperty("是否完成")
    @TableField("is_finish")
    private String isFinish;

    @ApiModelProperty("电梯ID")
    @TableField("elevator_id")
    private Integer elevatorId;

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
