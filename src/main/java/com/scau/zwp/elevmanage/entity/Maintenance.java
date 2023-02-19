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
 * 维修报告
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Getter
@Setter
@TableName("em_maintenance")
@ApiModel(value = "Maintenance对象", description = "维修报告")
public class Maintenance {

    @ApiModelProperty("维修报告ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("维修编号")
    @TableField("maintenance_number")
    private String maintenanceNumber;

    @ApiModelProperty("电梯编号")
    @TableField("elevator_number")
    private String elevatorNumber;

    @ApiModelProperty("电梯地点")
    @TableField("location_name")
    private String locationName;

    @ApiModelProperty("联系人")
    @TableField("contact_person")
    private String contactPerson;

    @ApiModelProperty("联系电话")
    @TableField("load_speed")
    private Integer loadSpeed;

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
    private LocalDateTime maintenanceData;

    @ApiModelProperty("维修人")
    @TableField("maintenance_person")
    private String maintenancePerson;

    @ApiModelProperty("是否完成")
    @TableField("is_finish")
    private String isFinish;

    @ApiModelProperty("电梯ID")
    @TableField("elevator_id")
    private String elevatorId;

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
