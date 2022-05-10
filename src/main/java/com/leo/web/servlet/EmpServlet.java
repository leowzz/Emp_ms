package com.leo.web.servlet;


import com.alibaba.fastjson.JSON;
import com.leo.pojo.Employee;
import com.leo.pojo.Manager;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/emp/*")
public class EmpServlet extends BaseServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    
    public void login(HttpServletRequest req,
                      HttpServletResponse res) throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        int id = (Integer) JSON.parseObject(params).get("id");
        //转为Employee对象
        Employee employee = JSON.parseObject(params, Employee.class);
        employee.setId(id);
        System.out.println(employee);
        // 查询
        Employee loginEmp = employeeService.login(employee.getId(), employee.getPasswd());
        System.out.println(loginEmp);
        if (loginEmp != null) {
            System.out.println("登录成功");
            //2. 转为JSON
            String jsonString = JSON.toJSONString(loginEmp);
            
            //3. 写数据
            res.setContentType("text/json;charset=utf-8");
            res.getWriter().write(jsonString);
            HttpSession session = req.getSession();
            session.setAttribute("employee", employee);
            
        }else {
            System.out.println("登录失败");
            res.getWriter().write("false");
        }
    }
    
    public void changePasswd(HttpServletRequest req,
                             HttpServletResponse res) throws ServletException, IOException {
        //1. 接收数据
        int id =  Integer.parseInt(req.getParameter("id"));
        String newPasswd = req.getParameter("newPasswd");
        //2. 调用service更新
        employeeService.changePasswd(id, newPasswd);
        //3. 返回结果
        res.getWriter().write("success");
    }
    
    public void updateSelfInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        System.out.println("json: " + params);
        //转为Employee对象
        Employee employee = JSON.parseObject(params, Employee.class);
        System.out.println(employee);
        //2. 调用service更新
        employeeService.updateEmp(employee);
        //3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\"}");
    }
    
}
