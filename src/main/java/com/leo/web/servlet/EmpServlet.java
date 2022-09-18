package com.leo.web.servlet;

import com.leo.pojo.Employee;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.EmployeeServiceImpl;

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
    
    public void login(HttpServletRequest req,
                      HttpServletResponse res) throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        logger.info("req read: {}", params);
        //转为Employee对象
        Employee employee = JSON.parseObject(params, Employee.class);
        if (employee == null) {
            logger.error("json解析错误: {}", br.read());
            res.getWriter().write("err");
            return;
        }
        logger.info("parsed employee: emp.id: {}, emp.passwd: {}", employee.getId(), employee.getPasswd());
        // 查询
        Employee loginEmp = employeeService.login(employee.getId(), employee.getPasswd());
        if (loginEmp != null) {
            logger.info("登录成功");
            logger.info("selected employee: {}", loginEmp);
            //2. 转为JSON
            String jsonString = JSON.toJSONString(loginEmp);
            //3. 写数据
            res.setContentType("text/json;charset=utf-8");
            res.getWriter().write(jsonString);
        } else {
            logger.info("登录失败");
            res.getWriter().write("false");
        }
    }
    
    public void changePasswd(HttpServletRequest req,
                             HttpServletResponse res) throws ServletException, IOException {
        //1. 接收数据
        int id = Integer.parseInt(req.getParameter("id"));
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
        logger.info("json: {}", params);
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
