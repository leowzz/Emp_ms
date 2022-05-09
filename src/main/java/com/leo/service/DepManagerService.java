package com.leo.service;

import com.leo.pojo.Employee;
import com.leo.pojo.Job;

import java.util.List;

public interface DepManagerService {
    
    List<Employee> selectAllEmployees(int dep_id);
    
    Employee selectById(int id);
    
    int selectJobIdByName(String name);
    
    List<Employee> selectByName(String name);
    
    String[] selectJobs(int dep_id);
    
    List<Job> selectJobInfos(int dep_id);
    
    int selectIdByName(String name);
    
    void insertJob(Job job);
    
    void updateJobSalary(Job job);
    void insertEmp(Employee emp);
    
    boolean deleteEmp(int id);
    
    void updateEmp(Employee emp);
}
