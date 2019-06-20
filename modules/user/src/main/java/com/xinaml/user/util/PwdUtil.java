package com.xinaml.user.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: [lgq]
 * @Date: [19-6-20 上午9:22]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class PwdUtil {
    private static final BCryptPasswordEncoder PWD_ENCODER = new BCryptPasswordEncoder();
    public static  String encode(String pwd){
        return PWD_ENCODER.encode(pwd);
    }
    public static  boolean verify(String pwd,String encodedPwd){
        return PWD_ENCODER.matches(pwd,encodedPwd);
    }
}
