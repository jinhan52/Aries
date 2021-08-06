package com.kim.security.aries.common;

public class ResultStatus {
    //正常成功通过
    public static final Integer SUCCESS = 20000;

    //正常失败
    public static final Integer ERROR = 50000;

    //没有权限
    public static final Integer UNAUTHORIZED = 40003;

    //认证失败
    public static final Integer UNAUTH = 40005;

}
