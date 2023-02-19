package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 检查报告图片
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Getter
@Setter
@TableName("em_inspection_image")
@ApiModel(value = "InspectionImage对象", description = "检查报告图片")
public class InspectionImage {

    @ApiModelProperty("检查报告图片ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("检查报告ID")
    @TableField("inspection_id")
    private String inspectionId;

    @ApiModelProperty("维修报告ID")
    @TableField("maintenance_id")
    private String maintenanceId;

    @ApiModelProperty("图片名")
    @TableField("image_name")
    private String imageName;

    @ApiModelProperty("图片位置")
    @TableField("image_position")
    private String imagePosition;

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
