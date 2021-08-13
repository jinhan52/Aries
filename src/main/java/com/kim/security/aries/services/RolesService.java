package com.kim.security.aries.services;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.model.Roles;
import com.baomidou.mybatisplus.extension.service.IService;
public interface RolesService extends IService<Roles>{
    DataResult getAllRoles(Integer currentPage, Integer pageSize, String search);

    DataResult getAllPermission();

    DataResult addRolePermission(JSONObject jsonObject);

    DataResult updateRolePermission(JSONObject jsonObject);

    DataResult deleteRolePermission(JSONObject jsonObject);
}
