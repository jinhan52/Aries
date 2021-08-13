package com.kim.security.aries.services.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.mapper.AriespermissionMapper;
import com.kim.security.aries.model.Ariespermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kim.security.aries.mapper.AriesrolesMapper;
import com.kim.security.aries.model.Ariesroles;
import com.kim.security.aries.services.AriesrolesService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AriesrolesServiceImpl extends ServiceImpl<AriesrolesMapper, Ariesroles> implements AriesrolesService{

    @Autowired
    AriespermissionMapper ariespermissionMapper;

    @Override
    public DataResult getAllRoles(Integer currentPage, Integer pageSize,String search) {
        currentPage = (currentPage - 1) * pageSize;
        Integer rolesCount = baseMapper.getRolesCount(search);
        List<Ariesroles> allRoles = baseMapper.getAllRoles(currentPage,pageSize,search);
        Map resultmap = new HashMap();
        resultmap.put("data",allRoles);
        resultmap.put("count",rolesCount);
        return DataResult.successWithData(resultmap);
    }

    @Override
    public DataResult getAllPermission() {
        List<Ariespermission> allPermission = ariespermissionMapper.getAllPermission();
        return DataResult.successWithData(allPermission);
    }

    @Override
    @Transactional
    public DataResult addRolePermission(JSONObject jsonObject) {
        String rolename = jsonObject.getStr("rolename");
        String[] permissions = jsonObject.get("permissions", String[].class);
        Ariesroles ariesroles = new Ariesroles();
        ariesroles.setRolename(rolename);
        baseMapper.insertRoleWithId(ariesroles);
        Integer roleid = ariesroles.getRoleid();
        baseMapper.insertRolePermission(roleid,permissions);

        return DataResult.success();
    }

    @Override
    @Transactional
    public DataResult updateRolePermission(JSONObject jsonObject) {
        String rolename = jsonObject.getStr("rolename");
        String[] permissions = jsonObject.get("permissions", String[].class);
        Integer roleid = jsonObject.getInt("roleid");
        Ariesroles ariesroles = new Ariesroles();
        ariesroles.setRoleid(roleid);
        ariesroles.setRolename(rolename);
        baseMapper.updateById(ariesroles);
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
