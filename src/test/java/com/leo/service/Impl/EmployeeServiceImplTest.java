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
    public void insertEmpTest(){
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee e = new Employee("test", "test", "男", "202123555566664444", "2022-05-07",
                "test", "13113311331", 2, 1);
        mapper.createEmp(e);
        sqlSession.commit();
        sqlSession.close();
    }
    
    @Test
    public void deleteEmpByIdTest(){
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.deleteEmpById(8501);
    
        sqlSession.commit();
        sqlSession.close();
    }
    
    @Test
    public void TestLogin() {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee e = mapper.login(8001, "031214");
        System.out.println(e);
        sqlSession.close();
    }
}
