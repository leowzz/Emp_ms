package com.leo.service.Impl;

import com.leo.mapper.EmpMapper;
import com.leo.pojo.Employee;
import com.leo.service.EmployeeService;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class EmployeeServiceImpl implements EmployeeService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    public Employee login(int id, String passwd) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee m = mapper.login(id, passwd);
        sqlSession.close();
        return m;
    }
    
    public boolean updateEmp(Employee emp) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.updateEmp(emp);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
    
    public boolean changePasswd(int id, String passwd) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.changePasswd(id, passwd);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
