package com.leo.service.Impl;

import com.leo.mapper.DepManagerMapper;
import com.leo.mapper.EmpMapper;
import com.leo.pojo.Employee;
import com.leo.service.DepManagerService;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DepManagerServiceImplTest {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    DepManagerService depManagerService = new DepManagerServiceImpl();
    
    @Test
    public void selectJobsTest() {
        // 初始化
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        // 测试部分
        String[] jobs = mapper.selectJobs(1);
        System.out.println(Arrays.toString(jobs));
        
        
        // 收尾部分
        sqlSession.close();
    }
    
    @Test
    public void testSelectEmployeesByName() {
        List<Employee> es = depManagerService.searchEmployeesByName("苗无光", 2);
        System.out.println(es);
        for (Employee e : es) {
            System.out.println(e);
        }
    }
    
    @Test
    public void selectManagerIdOfDep(){
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        System.out.println(managerService.selectManagerIdOfDep(2));
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
    
    @Test
    public void selectAllEmployees() {
    }
    
    @Test
    public void searchEmployeesByName() {
    }
    
    @Test
    public void selectById() {
    }
    
    @Test
    public void selectByName() {
    }
    
    @Test
    public void selectIdByName() {
    }
    
    @Test
    public void selectJobIdByName() {
    }
    
    @Test
    public void selectJobs() {
    }
    
    @Test
    public void selectJobInfos() {
    }
    
    @Test
    public void insertEmp() {
    }
    
    @Test
    public void insertJob() {
    }
    
    @Test
    public void updateJobSalary() {
    }
    
    @Test
    public void updateEmp() {
    }
    
    @Test
    public void deleteEmp() {
    }
}
