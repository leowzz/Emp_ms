package com.leo.service;

import com.leo.pojo.Employee;

import java.util.List;

public interface DepManagerService {
    
    List<Employee> selectAllEmployees(int dep_id);
    
    Employee selectById(int id);
    
    int selectJobIdByName(String name);
    
    List<Employee> selectByName(String name);
    
    String[] selectJobs(int dep_id);
    
    int selectIdByName(String name);
    
    boolean insertEmp(Employee emp);
    
    boolean deleteEmp(int id);
    
    boolean updateEmp(Employee emp);
}
