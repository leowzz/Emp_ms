package com.leo.util;

import com.leo.web.servlet.EmpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description
 * @date 2023/5/22 15:30
 **/
public class CookieUtils {
    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);
    
    public static String getCookie(Cookie[] cookies, String name) {
        String res = null;
        if (cookies == null) {
            logger.info("cookie is null");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    res = cookie.getValue();
                    logger.info("cookie: name:{}, value: {}", name, res);
                }
            }
        }
        return res;
    }
    
    public static void delCookie(HttpServletResponse res, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        res.addCookie(cookie);
    }
    
    public static void setCookie(HttpServletResponse res, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        res.addCookie(cookie);
    }
    
    public static void setCookie(HttpServletResponse res, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        res.addCookie(cookie);
    }
}
