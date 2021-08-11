package com.kim.security.aries.services;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.model.Ariespermission;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AriespermissionService extends IService<Ariespermission> {
    DataResult initMenuData(Integer currentPage, Integer pageSizem);

    DataResult initMenuDataWithSearch(Integer currentPage, Integer pageSize,String search);

    DataResult initMenuParentData();

    DataResult addNewMenu(JSONObject jsonObject);

    DataResult eiditorMenu(JSONObject jsonObject);

    DataResult deleteMenu(JSONObject jsonObject);
}

