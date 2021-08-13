package com.kim.security.aries.services.impl;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.mapper.PermissionMapper;
import com.kim.security.aries.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kim.security.aries.mapper.RolesMapper;
import com.kim.security.aries.model.Roles;
import com.kim.security.aries.services.RolesService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService{
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public DataResult getAllRoles(Integer currentPage, Integer pageSize,String search) {
        currentPage = (currentPage - 1) * pageSize;
        Integer rolesCount = baseMapper.getRolesCount(search);
        List<Roles> allRoles = baseMapper.getAllRoles(currentPage,pageSize,search);
        Map resultmap = new HashMap();
        resultmap.put("data",allRoles);
        resultmap.put("count",rolesCount);
        return DataResult.successWithData(resultmap);
    }

    @Override
    public DataResult getAllPermission() {
        List<Permission> allPermission = permissionMapper.getAllPermission();
        return DataResult.successWithData(allPermission);
    }

    @Override
    @Transactional
    public DataResult addRolePermission(JSONObject jsonObject) {
        String rolename = jsonObject.getStr("rolename");
        String[] permissions = jsonObject.get("permissions", String[].class);
        Roles roles = new Roles();
        roles.setRolename(rolename);
        baseMapper.insertRoleWithId(roles);
        Integer roleid = roles.getRoleid();
        baseMapper.insertRolePermission(roleid,permissions);

        return DataResult.success();
    }

    @Override
    @Transactional
    public DataResult updateRolePermission(JSONObject jsonObject) {
        String rolename = jsonObject.getStr("rolename");
        String[] permissions = jsonObject.get("permissions", String[].class);
        Integer roleid = jsonObject.getInt("roleid");
        Roles roles = new Roles();
        roles.setRoleid(roleid);
        roles.setRolename(rolename);
        baseMapper.updateById(roles);
        baseMapper.removeAllPermission(roleid);
        baseMapper.insertRolePermission(roleid,permissions);
        return DataResult.success();
    }

    @Override
    @Transactional
    public DataResult deleteRolePermission(JSONObject jsonObject) {
        Integer roleid = jsonObject.getInt("roleid");
        if (roleid==1){
            return DataResult.failure("管理员角色不能删除");
        }
        baseMapper.deleteById(roleid);
        baseMapper.removeAllPermission(roleid);
        return DataResult.success();
    }
}
