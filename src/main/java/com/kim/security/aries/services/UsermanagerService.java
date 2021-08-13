package com.kim.security.aries.services;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.model.Usermanager;
import com.baomidou.mybatisplus.extension.service.IService;
public interface UsermanagerService extends IService<Usermanager>{
    DataResult initUsersData(Integer currentPage, Integer pageSize, String search);

    DataResult initRoleData();

    DataResult addNewUser(JSONObject jsonObject);

    DataResult updateUser(JSONObject jsonObject);

    DataResult deleteUser(JSONObject jsonObject);
}
