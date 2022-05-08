package com.leo.web.servlet;

import com.alibaba.fastjson.JSON;
import com.leo.pojo.Employee;
import com.leo.service.DepManagerService;
import com.leo.service.Impl.DepManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/dep/*")
public class DepManagerServlet extends BaseServlet {
    DepManagerService depManagerService = new DepManagerServiceImpl();
    
    public void selectAllEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        int dep_id = Integer.parseInt(request.getParameter("dep_id"));
        List<Employee> employees = depManagerService.selectAllEmployees(dep_id);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(employees);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    public void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println("json: " + params);
//        int id = (Integer) JSON.parseObject(params).get("id");
        //转为Employee对象
        Employee employee = JSON.parseObject(params, Employee.class);
        System.out.println(employee);
        //2. 调用service添加
        depManagerService.insertEmp(employee);
        //3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\",\"id\":" + employee.getId() + "}");
    }
    
    public void selectJobNames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dep_id = Integer.parseInt(request.getParameter("dep_id"));
        //1. 调用service查询
        String[] jobNames = depManagerService.selectJobs(dep_id);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(jobNames);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    public void updateEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println("json: " + params);
        //转为Employee对象
        Employee employee = JSON.parseObject(params, Employee.class);
        System.out.println(employee);
        //2. 调用service更新
        depManagerService.updateEmp(employee);
        //3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\"}");
    }

//    public void selectJobIdByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1. 调用service查询
//        request.getCharacterEncoding();
//        String name = new String(request.getParameter("job_name").getBytes("ISO-8859-1"), "UTF-8");
//        int job_id = depManagerService.selectJobIdByName(name);
//        //2. 转为JSON
//        String jsonString = JSON.toJSONString(job_id);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
}
