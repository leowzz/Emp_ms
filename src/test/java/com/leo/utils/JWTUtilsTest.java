package com.leo.utils;

import com.leo.pojo.User;
import com.leo.util.JWTUtils;
import org.junit.Test;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description
 * @date 2023/5/22 13:52
 **/
public class JWTUtilsTest {
    
    @Test
    public void testDeJWT() {
        User u = new User();
        u.setName("leo");
        u.setPasswd("123456123456");
        String jwt = JWTUtils.getToken(u);
        System.out.println(jwt);
        User user = JWTUtils.deToken(jwt);
        System.out.println(user);
    }
}
