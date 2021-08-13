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
@TableName(value = "permission")
public class Permission implements Serializable {
    @TableId(value = "permissionid", type = IdType.INPUT)
    private Integer permissionid;

    @TableField(value = "parentid")
    private Integer parentid;

    @TableField(value = "menu")
    private String menu;

    @TableField(value = "menuName")
    private String menuname;

    @TableField(value = "menuBaseUrl")
    private String menubaseurl;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "menuIcon")
    private String menuicon;

    private static final long serialVersionUID = 1L;

    public static final String COL_PERMISSIONID = "permissionid";

    public static final String COL_PARENTID = "parentid";

    public static final String COL_MENU = "menu";

    public static final String COL_MENUNAME = "menuName";

    public static final String COL_MENUBASEURL = "menuBaseUrl";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_MENUICON = "menuIcon";

    @TableField(exist = false)
    private List<Permission> children;
    @TableField(exist = false)
    private Permission parent;
}