package com.leo.utils;

import com.leo.pojo.People;
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
        String jwt = JWTUtils.getToken("leo", "123456");
        System.out.println(jwt);
        People people = JWTUtils.deToken(jwt);
        System.out.println(people);
    }
}
