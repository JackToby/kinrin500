package com.kinrin500.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangtao
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="User对象", description="")
@Repository
public class User extends Model<User> {


    @ApiModelProperty(value = "User ID")
    @TableId("id")
    private int id;

    @ApiModelProperty(value = "User name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "E-Mail address")
    @TableField("email")
    private String email;

    @TableField("password")
    private String password;

    @ApiModelProperty(value = "User status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "Create timestamp")
    @TableField("create_time")
    private String createTime;

    @ApiModelProperty(value = "Update timestamp")
    @TableField("update_time")
    private String updateTime;

    @TableField("language")
    private String language;

    @TableField("last_workspace_id")
    private String lastWorkspaceId;

    @TableField("last_organization_id")
    private String lastOrganizationId;

    @ApiModelProperty(value = "Phone number of user")
    @TableField("phone")
    private String phone;

    @TableField("source")
    private String source;

    @ApiModelProperty(value = "授权字段")
    @TableField("perms")
    private String perms;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
