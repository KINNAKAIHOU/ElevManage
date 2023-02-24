package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 检查报告
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Getter
@Setter
@TableName("em_inspection")
@ApiModel(value = "Inspection对象", description = "检查报告")
public class Inspection {

    @ApiModelProperty("检查报告ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("检查编号")
    @TableField("inspection_number")
    private String InspectionNumber;

    @ApiModelProperty("电梯编号")
    @TableField("elevator_number")
    private String elevatorNumber;

    @ApiModelProperty("电梯名称")
    @TableField("elevator_name")
    private String elevatorName;

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

    @ApiModelProperty("检查日期")
    @TableField("inspection_data")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inspectionData;

    @ApiModelProperty("检查人")
    @TableField("inspection_person")
    private String inspectionPerson;

    @ApiModelProperty("是否完成")
    @TableField("is_finish")
    private String isFinish;

    @ApiModelProperty("是否故障")
    @TableField("is_fault")
    private String isFault;

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
