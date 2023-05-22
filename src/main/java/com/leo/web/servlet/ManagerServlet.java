package com.leo.web.servlet;

import com.leo.pojo.Manager;
import com.leo.service.Impl.ManagerServiceImpl;
import com.leo.service.ManagerService;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/man/*")
public class ManagerServlet extends BaseServlet {
    
    ManagerService managerService = new ManagerServiceImpl();
    
    public void getAllDepInfo(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询部门的信息
        List<HashMap<String, String>> depInfos = managerService.selectAllDepInfo();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(depInfos);
        //3. 写数据传递到前端
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    public void changePasswd(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        //1. 接收前端数据(用户名和新密码)(更改密码)
        String name = request.getParameter("name");
        String newPasswd = request.getParameter("newPasswd");
        //2. 调用service更新,返回结果
        managerService.changePasswd(name, newPasswd);
        //3. 返回结果
        response.getWriter().write("success");
    }
    
    public void updateManagerOfDep(HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据
        int dep_id = Integer.parseInt(request.getParameter("d_id"));
        int manager_id = Integer.parseInt(request.getParameter("m_id"));
        //2. 调用service更新
        boolean changeFlag = managerService.updateManagerOfDep(dep_id, manager_id);
        //3. 返回结果,告诉前端是否更新成功
        if (changeFlag)
            response.getWriter().write("success");
        else
            response.getWriter().write("fail");
    }
    
    public void backupDatabase(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service更新,返回结果
        boolean backupFlag = managerService.backupDatabase();
        //2. 返回结果
        if (backupFlag)
            response.getWriter().write("success");
        else
            response.getWriter().write("fail");
    }
}
