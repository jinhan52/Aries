package com.kim.security.aries.services.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.mapper.AriesrolesMapper;
import com.kim.security.aries.model.Ariesroles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kim.security.aries.mapper.AriesusermanagerMapper;
import com.kim.security.aries.model.Ariesusermanager;
import com.kim.security.aries.services.AriesusermanagerService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AriesusermanagerServiceImpl extends ServiceImpl<AriesusermanagerMapper, Ariesusermanager> implements AriesusermanagerService{

    @Autowired
    AriesrolesMapper ariesrolesMapper;

    @Override
    public DataResult initUsersData(Integer currentPage, Integer pageSize, String search) {
        currentPage = (currentPage - 1) * pageSize;
        List<Ariesusermanager> ariesusermanagers = baseMapper.initUsersData(currentPage, pageSize, search);
        Integer count = baseMapper.initUsersCount(search);

        Map resultMap = new HashMap();
        resultMap.put("data",ariesusermanagers);
        resultMap.put("count",count);
        return DataResult.successWithData(resultMap);
    }

    @Override
    public DataResult initRoleData() {
        List<Ariesroles> ariesroles = ariesrolesMapper.selectList(null);
        return DataResult.successWithData(ariesroles);
    }

    @Override
    @Transactional
    public DataResult addNewUser(JSONObject jsonObject) {
        Ariesusermanager ariesusermanager = jsonObject.toBean(Ariesusermanager.class);
        ariesusermanager.setPassword(SecureUtil.md5(SecureUtil.md5(ariesusermanager.getPassword())));

        ariesusermanager.setAvatar("https://thirdwx.qlogo.cn/mmopen/vi_32/NXoTIWG0iaT4VgibeHgprMYic8icqZkVWIpWQW12dKRcG2gUXvnT5BfsVfrA3oexIM8f6lnGP0RZRus2rGtChAySqQ/132");
        String[] roles = jsonObject.get("roles", String[].class);
        baseMapper.insertNewUser(ariesusermanager);
        if (roles.length>0){
            baseMapper.insertUserRoles(ariesusermanager.getUserid(),roles);
        }
        return DataResult.success();
    }

    @Override
    public DataResult updateUser(JSONObject jsonObject) {
        Ariesusermanager ariesusermanager = jsonObject.toBean(Ariesusermanager.class);

        if (ariesusermanager.getPassword().equals("******")){
            ariesusermanager.setPassword(null);
        }else{
            ariesusermanager.setPassword(SecureUtil.md5(SecureUtil.md5(ariesusermanager.getPassword())));
        }

        baseMapper.updateById(ariesusermanager);

        String[] roles = jsonObject.get("roles", String[].class);

        baseMapper.deleteUserRole(ariesusermanager.getUserid());
        if (roles.length>0){
            baseMapper.insertUserRoles(ariesusermanager.getUserid(),roles);
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
