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

// 部门主管service实现类
public class DepManagerServiceImpl implements DepManagerService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    // 根据部门id查询部门下的所有员工
    // 负责人: 范明俊
    public List<Employee> selectAllEmployees(int dep_id) {
        // 2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3. 获取Mapper对象
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        // 4. 调用方法
        List<Employee> es = mapper.selectEmployees(dep_id);
        // 5. 关闭资源
        sqlSession.close();
        // 6. 返回结果
        return es;
    }
    
    // 按照姓名模糊查询员工.
    // 负责人: 范明俊
    public List<Employee> searchEmployeesByName(String name, int dep_id) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        String search_name = "%" + name + "%";
        List<Employee> es = mapper.searchEmployeesByName(search_name, dep_id);
        sqlSession.close();
        return es;
    }
    
    // 根据员工id查询员工信息
    // 负责人: 范明俊
    public Employee selectById(int id) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Employee e = mapper.selectInfoById(id);
        sqlSession.close();
        return e;
    }
    
    // 根据员工姓名查询信息
    // 负责人: 范明俊
    public List<Employee> selectByName(String name) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Employee> employees = mapper.selectInfoByName(name);
        sqlSession.close();
        return employees;
    }
    
    // 根据员工姓名查询员工id
    // 负责人: 王骞
    public int selectIdByName(String name) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        int id = mapper.selectIdByName(name);
        sqlSession.close();
        return id;
    }
    
    // 根据职位名称查询职位编号
    // 负责人: 王骞
    public int selectJobIdByName(String name) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        int id = mapper.selectJobIdByName(name);
        sqlSession.close();
        return id;
    }
    
    // 查询部门下所有职位名称
    // 负责人: 王骞
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
    
    // 查询部门下所有职位的详细信息
    // 负责人: 王骞
    public List<Job> selectJobInfos(int dep_id) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        List<Job> jobs = mapper.selectJobInfos(dep_id);
        sqlSession.close();
        return jobs;
    }
    
    // 新建员工
    // 负责人: 王占泽
    public void insertEmp(Employee emp) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        emp.setJob_id(selectJobIdByName(emp.getJob_name()));
        mapper.createEmp(emp);
        sqlSession.commit();
        sqlSession.close();
    }
    
    // 新建职位
    // 负责人: 王占泽
    public void insertJob(Job job) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        mapper.insertJob(job);
        sqlSession.commit();
        sqlSession.close();
    }
    
    // 更新职位薪资
    // 负责人: 王骞
    public void updateJobSalary(Job job) {
        SqlSession sqlSession = factory.openSession();
        DepManagerMapper mapper = sqlSession.getMapper(DepManagerMapper.class);
        mapper.updateJobSalary(job);
        sqlSession.commit();
        sqlSession.close();
    }
    
    // 更新员工信息
    // 负责人: 王占泽
    public void updateEmp(Employee emp) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.updateEmp(emp);
        sqlSession.commit();
        sqlSession.close();
    }
    
    // 删除员工
    // 负责人: 范明俊
    public void deleteEmp(int id) {
        SqlSession sqlSession = factory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        mapper.deleteEmpById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
