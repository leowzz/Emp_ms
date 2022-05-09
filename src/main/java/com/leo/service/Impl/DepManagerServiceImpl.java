package com.leo.service.Impl;

import com.leo.mapper.DepManagerMapper;
import com.leo.mapper.EmpMapper;
import com.leo.pojo.Employee;
import com.leo.pojo.Job;
import com.leo.service.DepManagerService;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DepManagerServiceImpl implements DepManagerService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    public List<Employee> selectAllEmployees(int dep_id) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        List<Employee> es = mapper.selectEmployees(dep_id);
        sqlSession.close();
        return es;
    }
    
    public List<Employee> searchEmployeesByName(String name, int dep_id) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        String search_name = "%" + name + "%";
        List<Employee> es = mapper.searchEmployeesByName(search_name, dep_id);
        sqlSession.close();
        return es;
    }
    public Employee selectById(int id) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee e = mapper.selectInfoById(id);
        sqlSession.close();
        return e;
    }
    
    public List<Employee> selectByName(String name) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Employee> employees = mapper.selectInfoByName(name);
        sqlSession.close();
        return employees;
    }
    
    public int selectIdByName(String name) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        int id = mapper.selectIdByName(name);
        sqlSession.close();
        return id;
    }
    
    public int selectJobIdByName(String name) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        int id = mapper.selectJobIdByName(name);
        sqlSession.close();
        return id;
    }
    
    
    
    public String[] selectJobs(int dep_id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        //4. 调用方法
        String[] jobs = mapper.selectJobs(dep_id);
        //5. 释放资源
        sqlSession.close();
        return jobs;
    }
    
    public List<Job> selectJobInfos(int dep_id) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        List<Job> jobs = mapper.selectJobInfos(dep_id);
        sqlSession.close();
        return jobs;
    }
    
    public void insertEmp(Employee emp) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//        Employee e = new Employee("test", "test", "男", "202123555566664444", "2022-05-07",
//                "test", "13113311331", 2, 1);
        emp.setJob_id(selectJobIdByName(emp.getJob_name()));
        mapper.createEmp(emp);
        sqlSession.commit();
        sqlSession.close();
    
    }
    
    public void insertJob(Job job) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        mapper.insertJob(job);
        sqlSession.commit();
        sqlSession.close();
    }
    
    public void updateJobSalary(Job job) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        mapper.updateJobSalary(job);
        sqlSession.commit();
        sqlSession.close();
    }
    
    public void updateEmp(Employee emp) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.updateEmp(emp);
        sqlSession.commit();
        sqlSession.close();
    }
    
    public void deleteEmp(int id) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.deleteEmpById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
