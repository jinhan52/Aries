package com.kim.security.aries.services.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.mapper.RolesMapper;
import com.kim.security.aries.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kim.security.aries.model.Usermanager;
import com.kim.security.aries.mapper.UsermanagerMapper;
import com.kim.security.aries.services.UsermanagerService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsermanagerServiceImpl extends ServiceImpl<UsermanagerMapper, Usermanager> implements UsermanagerService{
    @Autowired
    RolesMapper rolesMapper;

    @Override
    public DataResult initUsersData(Integer currentPage, Integer pageSize, String search) {
        currentPage = (currentPage - 1) * pageSize;
        List<Usermanager> usermanagers = baseMapper.initUsersData(currentPage, pageSize, search);
        Integer count = baseMapper.initUsersCount(search);

        Map resultMap = new HashMap();
        resultMap.put("data",usermanagers);
        resultMap.put("count",count);
        return DataResult.successWithData(resultMap);
    }

    @Override
    public DataResult initRoleData() {
        List<Roles> ariesroles = rolesMapper.selectList(null);
        return DataResult.successWithData(ariesroles);
    }

    @Override
    @Transactional
    public DataResult addNewUser(JSONObject jsonObject) {
        Usermanager usermanager = jsonObject.toBean(Usermanager.class);
        usermanager.setPassword(SecureUtil.md5(SecureUtil.md5(usermanager.getPassword())));

        usermanager.setAvatar("https://thirdwx.qlogo.cn/mmopen/vi_32/NXoTIWG0iaT4VgibeHgprMYic8icqZkVWIpWQW12dKRcG2gUXvnT5BfsVfrA3oexIM8f6lnGP0RZRus2rGtChAySqQ/132");
        String[] roles = jsonObject.get("roles", String[].class);
        baseMapper.insertNewUser(usermanager);
        if (roles.length>0){
            baseMapper.insertUserRoles(usermanager.getUserid(),roles);
        }
        return DataResult.success();
    }

    @Override
    public DataResult updateUser(JSONObject jsonObject) {
        Usermanager usermanager = jsonObject.toBean(Usermanager.class);

        if (usermanager.getPassword().equals("******")){
            usermanager.setPassword(null);
        }else{
            usermanager.setPassword(SecureUtil.md5(SecureUtil.md5(usermanager.getPassword())));
        }

        baseMapper.updateById(usermanager);

        String[] roles = jsonObject.get("roles", String[].class);

        baseMapper.deleteUserRole(usermanager.getUserid());
        if (roles.length>0){
            baseMapper.insertUserRoles(usermanager.getUserid(),roles);
        }

        return DataResult.success();
    }

    @Override
    public DataResult deleteUser(JSONObject jsonObject) {
        Integer userid = jsonObject.getInt("userid");
        if (userid==1){
            return DataResult.failure("该用户为系统用户不可删除!");
        }
        baseMapper.deleteById(userid);
        baseMapper.deleteUserRole(userid);
        return DataResult.success();
    }
}
