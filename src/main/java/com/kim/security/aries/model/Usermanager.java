package com.kim.security.aries.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
@TableName(value = "usermanager")
public class Usermanager implements Serializable {
    @TableId(value = "userid", type = IdType.INPUT)
    private Integer userid;

    @TableField(value = "username")
    private String username;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "email")
    private String email;

    @TableField(value = "stauts")
    private Integer stauts;

    @TableField(value = "version")
    private Integer version;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    public List<Roles> allRolesList;

    private static final long serialVersionUID = 1L;

    public static final String COL_USERID = "userid";

    public static final String COL_USERNAME = "username";

    public static final String COL_PHONE = "phone";

    public static final String COL_PASSWORD = "password";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_EMAIL = "email";

    public static final String COL_STAUTS = "stauts";

    public static final String COL_VERSION = "version";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}