package com.young.edge.cloud.commen.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tornado Young
 * @date time 2020/3/2 21:24
 */
public class SystemConstant {
    public static final int JWT_ERRCODE_NULL = 1000;
    public static final int JWT_ERRCODE_EXPIRE = 1001;
    public static final int JWT_ERRCODE_FAIL = 1002;
    public static final String JWT_SECERT = "1qaz2wsx";
    public static final String TOKEN="token";
    public static final String USER_ID="userId";
    public static final String ERROR_MSG_COMMEN="请重试！";
    public static final String ADMIN_ROLE="1";
    public static final String ADMIN_ROLE_DESC="超级管理员";
    public final static Map<String,String> genderMap=new HashMap<>();
    static {
        genderMap.put("1","男");
        genderMap.put("2","女");
        genderMap.put("3","未知");
    }
}
