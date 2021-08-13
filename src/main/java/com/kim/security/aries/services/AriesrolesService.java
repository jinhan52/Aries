package com.kim.security.aries.services;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.model.Ariesroles;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

public interface AriesrolesService extends IService<Ariesroles>{
    DataResult getAllRoles(Integer currentPage, Integer pageSize,String search);

    DataResult getAllPermission();

    DataResult addRolePermission(JSONObject jsonObject);

    DataResult updateRolePermission(JSONObject jsonObject);

    DataResult deleteRolePermission(JSONObject jsonObject);
}
