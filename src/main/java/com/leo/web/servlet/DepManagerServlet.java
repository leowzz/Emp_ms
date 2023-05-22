package com.leo.web.servlet;

import com.leo.pojo.Employee;
import com.leo.pojo.Job;
import com.leo.service.DepManagerService;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.DepManagerServiceImpl;

import com.alibaba.fastjson.JSON;
import com.leo.service.Impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/dep/*")
public class DepManagerServlet extends BaseServlet {
    DepManagerService depManagerService = new DepManagerServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(DepManagerServlet.class);
    
    /*
     * 添加员工方法
     * 通过request获取要添加的员工信息, 不包括编号
     * 调用service层的createEmp方法, 调用成功后mapper层的方法回将新建员工的编号设置给传过去的employee对象
     * 将新建的员工编号写入response返回给前端
     */
    public void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Employee employee = JSON.parseObject(String.valueOf(params), Employee.class);
        System.out.println(employee);
        // 2. 调用service添加
        depManagerService.insertEmp(employee);
        // 3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\",\"id\":" + employee.getId() + "}");
    }
    
    // 新建职位
    public void insertJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        StringBuilder params = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            params.append(line);
        }
        logger.info("json: {}", params);
        // 转为Job对象
        Job job = JSON.parseObject(String.valueOf(params), Job.class);
        System.out.println(job);
        // 2. 调用service更新
        depManagerService.insertJob(job);
        // 3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\",\"id\":" + job.getId() + "}");
    }
    
    
    // 通过姓名模糊查询员工信息
    public void searchEmployeesByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        logger.debug("req.getParameter: {}", name);
        int dep_id = Integer.parseInt(request.getParameter("dep_id"));
        // 1. 调用service查询
        List<Employee> employees = depManagerService.searchEmployeesByName(name, dep_id);
        System.out.println(employees);
        // 2. 转为JSON
        String jsonString = JSON.toJSONString(employees);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    // 查询所有员工信息
    public void selectAllEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 调用service查询
        int dep_id = Integer.parseInt(request.getParameter("dep_id"));
        List<Employee> employees = depManagerService.selectAllEmployees(dep_id);
        // 2. 转为JSON
        String jsonString = JSON.toJSONString(employees);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
        
    }
    
    // 获取所有职位名称
    public void selectJobNames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dep_id = Integer.parseInt(request.getParameter("dep_id"));
        // 1. 调用service查询
        String[] jobNames = depManagerService.selectJobs(dep_id);
        // 2. 转为JSON
        String jsonString = JSON.toJSONString(jobNames);
        // 3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    // 获取所有职位详细信息
    public void selectJobInfos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dep_id = Integer.parseInt(request.getParameter("dep_id"));
        List<Job> jobs = depManagerService.selectJobInfos(dep_id);
        String jsonString = JSON.toJSONString(jobs);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    // 更新员工信息
    public void updateEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Employee emp = JSON.parseObject(String.valueOf(params), Employee.class);
        if (emp == null) {
            logger.error("json解析错误: {}", params);
            response.getWriter().write("err");
            return;
        }
        logger.info("emp: {}", emp);
        // 2. 调用service更新
        depManagerService.updateEmp(emp);
        // 3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\"}");
    }
    
    
    // 更新职位薪资
    public void updateJobSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        BufferedReader br = request.getReader();
        StringBuilder params = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            params.append(line);
        }
        System.out.println("json: " + params);
        // 转为Job对象
        Job job = JSON.parseObject(String.valueOf(params), Job.class);
        System.out.println(job);
        // 2. 调用service更新
        depManagerService.updateJobSalary(job);
        // 3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\"}");
    }
    
    // 删除员工
    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收数据
        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        // 2. 调用service更新
        depManagerService.deleteEmp(emp_id);
        // 3. 返回结果
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("{\"msg\":\"success\"}");
    }
    
}
