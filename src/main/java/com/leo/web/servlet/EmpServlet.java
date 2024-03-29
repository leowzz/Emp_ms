package com.leo.web.servlet;

import com.leo.pojo.Employee;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.EmployeeServiceImpl;

import com.leo.util.CookieUtils;
import com.leo.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/emp/*")
public class EmpServlet extends BaseServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    
    private static final Logger logger = LoggerFactory.getLogger(EmpServlet.class);
    
    public void changePasswd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 1. 接收数据
        int id = Integer.parseInt(req.getParameter("id"));
        String newPasswd = req.getParameter("newPasswd");
        // 2. 调用service更新
        employeeService.changePasswd(id, newPasswd);
        // 3. 返回结果
        res.getWriter().write("success");
    }
    
    public void updateSelfInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        StringBuilder params = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            params.append(line);
        }
        logger.info("json: {}", params);
        // 转为Employee对象
        Employee emp = JSON.parseObject(params.toString(), Employee.class);
        if (emp == null) {
            logger.error("json解析错误: {}", params);
            response.getWriter().write("err");
            return;
        }
        // 通过编号和密码查询员工信息, 判断是否合法
        Employee selectEmp = employeeService.login(emp.getId(), emp.getPasswd());
        if (selectEmp == null) {
            logger.info("密码错误: {}", emp);
            response.getWriter().write("false");
            return;
        }
        // 2. 调用service更新
        employeeService.updateEmp(emp);
        // 3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\"}");
    }
    
}
