package com.leo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.leo.pojo.People;
import jdk.nashorn.internal.parser.Token;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description JWT加解密
 * @date 2023/5/22 13:45
 **/
public class JWTUtils {
    private static final String secret_key = "c73d7990d4c1cc97021b63b91aa35833";
    
    // 生成token
    public static String getToken(String username, String password) {
        try {
            return JWT.create().withClaim("username", username).withClaim("password", password).sign(Algorithm.HMAC256(secret_key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 解密token
    public static People deToken(String token) {
        try {
            String username = JWT.decode(token).getClaim("username").asString();
            String password = JWT.decode(token).getClaim("password").asString();
            People people = new People();
            people.setName(username);
            people.setPassword(password);
            return people;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 获取token中的信息
    public static String get(String token, String arg) {
        try {
            return JWT.decode(token).getClaim(arg).asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 验证token
    public static boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret_key)).build().verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

