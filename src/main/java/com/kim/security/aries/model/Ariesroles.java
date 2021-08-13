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
@TableName(value = "ariesroles")
public class Ariesroles implements Serializable {
    @TableId(value = "roleid", type = IdType.INPUT)
    private Integer roleid;

    @TableField(value = "rolename")
    private String rolename;

    @TableField(value = "stauts")
    private Integer stauts;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    List<Ariespermission> allPermission;

    private static final long serialVersionUID = 1L;

    public static final String COL_ROLEID = "roleid";

    public static final String COL_ROLENAME = "rolename";

    public static final String COL_STAUTS = "stauts";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}