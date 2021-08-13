package com.kim.security.aries.services.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kim.security.aries.common.DataResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kim.security.aries.mapper.PermissionMapper;
import com.kim.security.aries.model.Permission;
import com.kim.security.aries.services.PermissionService;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{
    @Override
    public DataResult initMenuData(Integer currentPage, Integer pageSize) {
        currentPage = (currentPage - 1) * pageSize;
        List<Permission> permissions = baseMapper.initMenuData(currentPage, pageSize);

        Integer count = baseMapper.selectCount(null);
        Map<String, Object> resultmap = new HashMap<>();
        resultmap.put("count",count);
        resultmap.put("data",permissions);
        return DataResult.successWithData(resultmap);
    }

    @Override
    public DataResult initMenuDataWithSearch(Integer currentPage, Integer pageSize, String search) {
        currentPage = (currentPage - 1) * pageSize;
        List<Permission> permissions = baseMapper.initMenuDataWithSearch(currentPage, pageSize,search);

        Integer count = baseMapper.selectCountWithSearch(search);
        Map<String, Object> resultmap = new HashMap<>();
        resultmap.put("count",count);
        resultmap.put("data",permissions);
        return DataResult.successWithData(resultmap);
    }

    @Override
    public DataResult initMenuParentData() {
        List<Permission> permissions = baseMapper.initMenuParentData();
        return DataResult.successWithData(permissions);
    }

    @Override
    public DataResult addNewMenu(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
        Permission permission = jsonObject.toBean(Permission.class);
        baseMapper.insert(permission);
        List<Permission> allMenus = baseMapper.getAllMenus();
        return DataResult.successWithData(allMenus);
    }

    @Override
    public DataResult eiditorMenu(JSONObject jsonObject) {
        Permission ariespermission = jsonObject.toBean(Permission.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parentid",ariespermission.getPermissionid());
        Integer count = baseMapper.selectCount(queryWrapper);

        if (ariespermission.getParentid()!=null && count !=0){
            return DataResult.failure("操作失败,该菜单下还有子菜单,请重新修改!");
        }

        baseMapper.eiditorMenu(ariespermission);
        List<Permission> allMenus = baseMapper.getAllMenus();
        return DataResult.successWithData(allMenus);
    }

    @Override
    public DataResult deleteMenu(JSONObject jsonObject) {
        String permissionid = jsonObject.getStr("permissionid");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parentid",permissionid);
        if(Integer.parseInt(permissionid)<=7){
            return DataResult.failure("操作失败,系统菜单不可删除");
        }

        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            return DataResult.failure("操作失败,该菜单下还有子菜单,请重新修改!");
        }

        baseMapper.deleteById(permissionid);

        List<Permission> allMenus = baseMapper.getAllMenus();
        return DataResult.successWithData(allMenus);
    }
}
