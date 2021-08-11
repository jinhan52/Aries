package com.kim.security.aries.services;

import cn.hutool.json.JSONObject;
import com.kim.security.aries.common.DataResult;

public interface AuthenticationServices {

    DataResult authLogin(JSONObject jsonObject);
}
