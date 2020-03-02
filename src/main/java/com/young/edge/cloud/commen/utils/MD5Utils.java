package com.young.edge.cloud.commen.utils;

import org.springframework.util.DigestUtils;

/**
 * @author Tornado Young
 * @date time 2020/3/2 23:05
 */
public class MD5Utils {
    /**
     * 密码验证
     * @param passwordMd5
     * @param passwordTwo
     * @return
     */
    public static boolean isEqual(String passwordMd5,String passwordTwo){
        String passwordTwoMD5 = DigestUtils.md5DigestAsHex(passwordTwo.getBytes());
        return passwordMd5.equals(passwordTwoMD5);
    }
}
