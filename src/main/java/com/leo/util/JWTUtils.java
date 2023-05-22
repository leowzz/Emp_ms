package com.leo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.leo.pojo.User;
import com.leo.web.servlet.EmpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description JWT加解密
 * @date 2023/5/22 13:45
 **/
public class JWTUtils {
    private static final String SECRET_KEY = "c73d7990d4c1cc97021b63b91aa35833";
    private static final Logger logger = LoggerFactory.getLogger(EmpServlet.class);
    // 生成token
    public static String getToken(User user) {
        try {
            return JWT.create().
                    withClaim("id", user.getId()).
                    withClaim("username", user.getName()).
                    withClaim("password", user.getPasswd()).
                    sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getToken(String username) {
        try {
            return JWT.create().
                    withClaim("username", username).
                    sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getToken(Integer id) {
        try {
            return JWT.create().
                    withClaim("id", id).
                    sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 解密token
    public static User deToken(String token) {
        try {
            int id = JWT.decode(token).getClaim("id").asInt();
            String username = JWT.decode(token).getClaim("username").asString();
            String password = JWT.decode(token).getClaim("password").asString();
            User user = new User();
            user.setId(id);
            user.setName(username);
            user.setPasswd(password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("token解密失败, token: {}", token);
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
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

