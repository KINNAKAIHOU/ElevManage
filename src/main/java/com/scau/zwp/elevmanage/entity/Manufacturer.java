package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 电梯厂家
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Getter
@Setter
@TableName("em_manufacturer")
@ApiModel(value = "Manufacturer对象", description = "电梯厂家")
public class Manufacturer {

    @ApiModelProperty("厂家ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("厂家名称")
    @TableField("manufacturer_name")
    private String manufacturerName;

    @ApiModelProperty("联系人")
    @TableField("contact_person")
    private String contactPerson;

    @ApiModelProperty("联系电话")
    @TableField("load_speed")
    private Integer loadSpeed;

    @ApiModelProperty("手机")
    @TableField("telephone")
    private Integer telephone;

    @ApiModelProperty("编号前缀")
    @TableField("mobile_phone")
    private String mobilePhone;

    @ApiModelProperty("传真")
    @TableField("fax")
    private Integer fax;

    @ApiModelProperty("公司地址")
    @TableField("address")
    private String address;

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
