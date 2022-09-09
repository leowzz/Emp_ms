package com.leo.service.Impl;

import com.leo.mapper.EmpMapper;
import com.leo.pojo.Employee;
import com.leo.service.EmployeeService;
import com.leo.util.SqlSessionFactoryUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

// 普通员工service实现类
public class EmployeeServiceImpl implements EmployeeService {
    // 创建工厂对象, 以便获取mapper对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    // 登录方法, 登录成功返回员工对象, 失败返回null
    // 负责人: 卢鹏飞
    public Employee login(int id, String passwd) {
        // 获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 获取mapper对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        // 调用mapper的login方法
        Employee m = mapper.login(id, passwd);
        // 关闭sqlSession
        sqlSession.close();
        // 返回结果
        return m;
    }
    
    // 员工信息更新方法
    // 负责人: 王占泽
    public boolean updateEmp(Employee emp) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.updateEmp(emp);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
    
    // 员工更改密码方法
    // 负责人: 卢鹏飞
    public boolean changePasswd(int id, String passwd) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.changePasswd(id, passwd);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
