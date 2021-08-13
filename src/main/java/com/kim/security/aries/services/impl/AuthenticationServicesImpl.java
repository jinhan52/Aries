package com.kim.security.aries.services.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.mapper.PermissionMapper;
import com.kim.security.aries.mapper.UsermanagerMapper;
import com.kim.security.aries.model.Permission;
import com.kim.security.aries.model.Usermanager;
import com.kim.security.aries.services.AuthenticationServices;
import com.kim.security.aries.tools.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServicesImpl implements AuthenticationServices {

    @Autowired
    UsermanagerMapper usermanagerMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public DataResult authLogin(JSONObject jsonObject) {

        String password = SecureUtil.md5(SecureUtil.md5(jsonObject.getStr("password")));
        Usermanager ariesusermanager = usermanagerMapper.authLogin(jsonObject.getStr("phone"), password);
        if (ariesusermanager==null){
            return DataResult.failure("您的账号密码有误!请重新输入!");
        }else{
            //获取该用户所有的权限
            List<Permission> usersAllPermission = permissionMapper.getUsersAllPermission(ariesusermanager.getUserid());
            boolean isadmin = false;

            for (Permission permission : usersAllPermission) {
                if (permission.getPermissionid()==1){
                    isadmin = true;
                }
            }

            List<String> auths = new ArrayList<>();

            if (isadmin){
                auths = ListUtil.list(false,"/");
                usersAllPermission = permissionMapper.getAllMenus();
            }else{
                for (Permission ariespermission : usersAllPermission) {
                    auths.add(ariespermission.getMenubaseurl());
                }
            }


            Map<String,Object> claims = new HashMap<>();
            claims.put("userid",ariesusermanager.getUserid());
            claims.put("phone",ariesusermanager.getPhone());
            claims.put("username",ariesusermanager.getUsername());
            claims.put("auths",auths);

            String token = JwtTools.getToken(claims);
            String refreshToken = JwtTools.getRefreshToken(claims);

            Map<String, Object> resultmap = new HashMap<>();
            resultmap.put("user",ariesusermanager);
            resultmap.put("menu",usersAllPermission);

            return DataResult.successWithToken("登录成功!",resultmap,new DataResult.IToken(token,refreshToken));
        }

    }

}
