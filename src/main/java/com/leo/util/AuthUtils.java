package com.leo.util;

import javax.servlet.http.Cookie;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description
 * @date 2023/5/22 16:34
 **/
public class AuthUtils {
    public static boolean checkCookie(Cookie[] cookies) {
        String token = CookieUtils.getCookie(cookies, "token");
        return JWTUtils.verify(token);
    }
}
