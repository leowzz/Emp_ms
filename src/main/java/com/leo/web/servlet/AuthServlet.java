package com.leo.web.servlet; /**
 * @author Leo Wang
 * @version 1.0.0
 * @description $desc
 * @date 2023/5/22 15:53
 **/

import com.alibaba.fastjson.JSON;
import com.leo.pojo.Employee;
import com.leo.pojo.User;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.EmployeeServiceImpl;
import com.leo.service.Impl.ManagerServiceImpl;
import com.leo.service.ManagerService;
import com.leo.util.CookieUtils;
import com.leo.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/auth/*")
public class AuthServlet extends BaseServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    ManagerService managerService = new ManagerServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(AuthServlet.class);
    
    
    public void ck_login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String token = CookieUtils.getCookie(req.getCookies(), "token");
        logger.debug("token: {}", token);
        if (token == null) {
            logger.info("token为空, 未登录");
            res.getWriter().write("false");
            return;
        }
        User user = JWTUtils.deToken(token);
        if (user == null) {
            logger.debug("token解析失败");
            res.getWriter().write("false");
            return;
        }
        logger.debug("token解析成功: {}", user);
        
        if (user.getId() < 5000) {
            // 管理员
            logger.info("管理员登录");
            if (!managerService.login(user.getName(), user.getPasswd())) {
                res.getWriter().write("false");
                return;
            }
            logger.info("登录成功: {}", user);
            res.setContentType("text/json;charset=utf-8");
            res.getWriter().write(JSON.toJSONString(user));
        } else {
            // 员工
            logger.info("员工登录");
            Employee emp = employeeService.login(user.getId(), user.getPasswd());
            if (emp == null) {
                res.getWriter().write("false");
                return;
            }
            logger.info("登录成功: {}", emp);
            res.setContentType("text/json;charset=utf-8");
            res.getWriter().write(JSON.toJSONString(emp));
        }
    }
    
    public void emp_login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 1. 接收数据
        String params = getParams(req);
        // 转为Employee对象
        Employee emp = JSON.parseObject(params, Employee.class);
        if (emp == null) {
            logger.error("json解析错误: {}", params);
            res.getWriter().write("false");
            return;
        }
        logger.info("parsed employee: emp.id: {}, emp.passwd: {}", emp.getId(), emp.getPasswd());
        // 查询
        Employee loginEmp = employeeService.login(emp.getId(), emp.getPasswd());
        if (loginEmp != null) {
            // 1. 写入cookie
            CookieUtils.setCookie(
                    res,
                    "token",
                    JWTUtils.getToken(loginEmp),
                    60 * 60 * 24 * 7
            );
            logger.info("登录成功");
            logger.info("selected employee: {}", loginEmp);
            // 2. 转为JSON
            String jsonString = JSON.toJSONString(loginEmp);
            // 3. 写数据
            res.setContentType("text/json;charset=utf-8");
            res.getWriter().write(jsonString);
            return;
        }
        logger.info("登录失败");
        res.getWriter().write("false");
    }
    
    private static String getParams(HttpServletRequest req) throws IOException {
        BufferedReader br = req.getReader();
        StringBuilder params = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            params.append(line);
        }
        logger.info("req read: {}", params);
        return String.valueOf(params);
    }
    
    public void emp_logout(HttpServletRequest req, HttpServletResponse res) {
    
    }
    
    public void admin_login(HttpServletRequest req, HttpServletResponse res) {
    
    }
    
    public void admin_logout(HttpServletRequest req, HttpServletResponse res) {
    
    }
    
}
