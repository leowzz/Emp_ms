package com.leo.service;

import com.leo.pojo.Employee;

public interface EmployeeService {
    Employee login(int id, String passwd);
    
    boolean updateEmp(Employee emp);
    
    boolean changePasswd(int id, String passwd);
}
