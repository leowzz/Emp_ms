package com.leo.service.Impl;

import com.leo.mapper.DepManagerMapper;
import com.leo.mapper.EmpMapper;
import com.leo.pojo.Employee;
import com.leo.service.DepManagerService;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

public class DepManagerServiceImplTest {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    DepManagerService depManagerService = new DepManagerServiceImpl();
    
    @Test
    public void selectJobsTest() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        
        //4. 调用方法
        String[] jobs = mapper.selectJobs(1);
        System.out.println(jobs);
        //5. 释放资源
        sqlSession.close();
        for (String job : jobs) {
            System.out.println(job);
            
        }
    }
    
    @Test
    public void testSelectEmployeesByName() {
        List<Employee> es = depManagerService.searchEmployeesByName("苗无光",2);
        System.out.println(es);
        for (Employee e : es) {
            System.out.println(e);
        }
    
    }
    
    @Test
    public void testSelectAllEmployees() {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        List<Employee> es = mapper.selectEmployees(2);
        sqlSession.close();
        
        for (Employee e : es) {
            System.out.println(e);
        }
    }
    
    @Test
    public void testInsertEmp() {
        Employee employee = new Employee("test22", "123123", "男", "12321321321",
                "2002-02-12", "456465465", "45465454", 2, 13);
        employee.setJob_name("会计");
        depManagerService.insertEmp(employee);
        System.out.println(employee.getId());
        
    }
}
