package com.leo.web.servlet;

import com.alibaba.fastjson.JSON;
import com.leo.pojo.Employee;
import com.leo.service.DepManagerService;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.DepManagerServiceImpl;
import com.leo.service.Impl.EmployeeServiceImpl;
import com.leo.service.Impl.ManagerServiceImpl;
import com.leo.service.ManagerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    ManagerService managerService = new ManagerServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
    
        //转为Brand对象
//        Brand brand = JSON.parseObject(params, Brand.class);
        //2. 转为JSON
//        String jsonString = JSON.toJSONString(map);
        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
