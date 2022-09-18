package com.leo.service.Impl;

import com.leo.mapper.EmpMapper;
import com.leo.pojo.Employee;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class EmployeeServiceImplTest {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    
    @Test
    public void login() {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        
        Employee e = mapper.login(8001, "031214");
        System.out.println(e);
        
        sqlSession.close();
    }
    
    
    @Test
    public void updateEmp() {
    }
    
    @Test
    public void changePasswd() {
    }
}
