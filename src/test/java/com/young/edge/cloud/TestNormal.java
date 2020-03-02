package com.young.edge.cloud;

import org.springframework.util.DigestUtils;

/**
 * @author Tornado Young
 * @date time 2020/3/2 23:28
 */
public class TestNormal {
    public static void main(String[] args) {
        String s = DigestUtils.md5DigestAsHex("111111".getBytes());
        System.out.println(s);
    }
}
