package com.leo.web.servlet;

import com.leo.pojo.Employee;
import com.leo.service.EmployeeService;
import com.leo.service.Impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EmpServlet.class);
    static EmployeeService employeeService = new EmployeeServiceImpl();
    
    private static boolean isLegalOperation(Employee emp, HttpServletResponse response) throws IOException {
        // 通过编号和密码查询员工信息, 判断是否合法
        Employee selectEmp = employeeService.login(emp.getId(), emp.getPasswd());
        if (selectEmp == null) {
            logger.info("密码错误: {}", emp);
            response.getWriter().write("false");
            return false;
        }
        return true;
    }
    
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 获取请求路径
        String uri = req.getRequestURI();
//        System.out.println(uri);
        // 获取最后一个 '/' 的下标
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
//        System.out.println(methodName);
        // 执行方法v
        // 获取EmpServlet字节码对象
        Class<? extends BaseServlet> cls = this.getClass();
        // 获取方法对象
        try {
            
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method.invoke(this, req, res);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            logger.warn(e.getLocalizedMessage());
            logger.warn("没有找到方法: {}", methodName);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.warn(e.getLocalizedMessage());
            logger.warn("方法执行失败: {}", methodName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.warn(e.getLocalizedMessage());
            logger.warn("方法不可访问: {}", methodName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e.getLocalizedMessage());
        }
    }
}
/*
 *　　　　　　　　┏┓　　　┏┓+ +
 *　　　　　　　┏┛┻━━━┛┻┓ + +
 *　　　　　　　┃　　　　　　　┃*　　　　　　　┃　　　━　　　┃ ++ + + +
 *　　　　　　 ████━████ ┃+
 *　　　　　　　┃　　　　　　　┃ +
 *　　　　　　　┃　　　┻　　　┃
 *　　　　　　　┃　　　　　　　┃ + +
 *　　　　　　　┗━┓　　　┏━┛
 *　　　　　　　　　┃　　　┃*　　　　　　　　　┃　　　┃ + + + +
 *　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting*　　　　　　　　　┃　　　┃ + 　　　　神兽保佑, 代码无 bug*　　　　　　　　　┃　　　┃
 *　　　　　　　　　┃　　　┃　　+*　　　　　　　　　┃　 　　┗━━━┓ + +
 *　　　　　　　　　┃ 　　　　　　　┣┓
 *　　　　　　　　　┃ 　　　　　　　┏┛
 *　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 *　　　　　　　　　　┃┫┫　┃┫┫
 *　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */