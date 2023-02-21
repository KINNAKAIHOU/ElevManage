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
 * 检查计划
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Getter
@Setter
@TableName("em_plan")
@ApiModel(value = "Plan对象", description = "检查计划")
public class Plan {

    @ApiModelProperty("检查计划ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("计划编号")
    @TableField("plan_number")
    private String planNumber;

    @ApiModelProperty("电梯编号")
    @TableField("elevator_number")
    private String elevatorNumber;

    @ApiModelProperty("电梯ID")
    @TableField("elevator_id")
    private Integer elevatorId;

    @ApiModelProperty("开始日期")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("结束日期")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("下一次检查日期")
    @TableField("next_time")
    private LocalDateTime nextTime;

    @ApiModelProperty("间隔时间")
    @TableField("interval_time")
    private Integer intervalTime;

    @ApiModelProperty("间隔时间单位")
    @TableField("Interval_unit")
    private Integer intervalUnit;

    @ApiModelProperty("是否完成")
    @TableField("is_finish")
    private String isFinish;

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
