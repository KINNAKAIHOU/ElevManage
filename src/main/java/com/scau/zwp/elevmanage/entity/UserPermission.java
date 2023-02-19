package com.scau.zwp.elevmanage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户权限
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-18 09:44:33
 */
@Getter
@Setter
@TableName("t_user_permission")
@ApiModel(value = "TUserPermission对象", description = "用户权限")
public class UserPermission {

    @ApiModelProperty("用户权限ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("权限ID")
    @TableField("permission_id")
    private String permissionId;

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
