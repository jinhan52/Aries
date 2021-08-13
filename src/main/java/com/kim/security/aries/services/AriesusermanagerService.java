package com.kim.security.aries.services;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.model.Ariesusermanager;
import com.baomidou.mybatisplus.extension.service.IService;
public interface AriesusermanagerService extends IService<Ariesusermanager>{

    DataResult initUsersData(Integer currentPage, Integer pageSize, String search);

    DataResult initRoleData();

    DataResult addNewUser(JSONObject jsonObject);

    DataResult updateUser(JSONObject jsonObject);

    DataResult deleteUser(JSONObject jsonObject);
}
