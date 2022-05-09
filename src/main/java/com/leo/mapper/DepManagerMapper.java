package com.leo.mapper;

import com.leo.pojo.Employee;
import com.leo.pojo.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.jws.WebResult;
import java.util.List;

public interface DepManagerMapper {
    
    @Select("select job_name from emp_ms.job where dep_id = #{depId}")
    String[] selectJobs(int depId);
    
    @Select("select * from emp_ms.job where dep_id = #{dep_id}")
    List<Job> selectJobInfos(int dep_id);
    
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, job.salary, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and d.id=#{dep_id};")
//    @ResultMap("birthResultMap")
    List<Employee> selectEmployees(int dep_id);
    
    @Select("select id from emp_ms.job where job_name = #{job_name}")
    int selectJobIdByName(String jobName);
    
    
    void insertJob(Job job);
    
    @Update("update emp_ms.job set salary = #{salary} where id = #{id}")
    void updateJobSalary(Job job);
    
    
}
