package com.leo.mapper;

import com.leo.pojo.Employee;
import com.leo.pojo.Job;

import org.apache.ibatis.annotations.*;

import java.util.List;

// 部门主管Mapper
public interface DepManagerMapper {
    
    /*查询部门下所有职位名称
    * 负责人: 王骞 */
    @Select("select job_name from emp_ms.job where dep_id = #{depId}")
    String[] selectJobs(int depId);
    
    /*查询所有职位详细信息
    * 负责人: 王骞 */
    @Select("select * from emp_ms.job where dep_id = #{dep_id}")
    List<Job> selectJobInfos(int dep_id);
    
    /*查询部门下所有员工信息
    * 负责人: 范明俊 */
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, job.salary, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and d.id=#{dep_id};")
    List<Employee> selectEmployees(int dep_id);
    
    /*通过姓名查询员工
    * 负责人: 王骞 */
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, job.salary, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.name like #{name} and e.dep_id = #{dep_id}; ")
    List<Employee> searchEmployeesByName(@Param("name") String name, @Param("dep_id") Integer dep_id);
    
    /*通过姓名查询职位
    * 负责人: 王骞 */
    @Select("select id from emp_ms.job where job_name = #{job_name}")
    int selectJobIdByName(String jobName);
    
    /*新建职位, 新建成功后自动会将新建的职位编号赋给job对象
    * 负责人: 王占泽 */
    void insertJob(Job job);
    
    /*更改职位薪资
    * 负责人: 王骞 */
    @Update("update emp_ms.job set salary = #{salary} where id = #{id}")
    void updateJobSalary(Job job);
    
    
}
