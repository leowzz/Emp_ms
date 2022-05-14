package com.leo.mapper;

import com.leo.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmpMapper {
    
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.id=#{id};")
    Employee selectInfoById(int id);
    
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.name=#{name};")
    List<Employee> selectInfoByName(String name);
    
    @Select("select id from emp_ms.employee where name = #{name};")
    int selectIdByName(String name);
    
    int createEmp(Employee emp);
    
    @Update("update emp_ms.employee set passwd = #{passwd} where id = #{id};")
    void changePasswd(@Param("id") int id, @Param("passwd") String passwd);
    
    @Update("update emp_ms.employee set name = #{name}, passwd = #{passwd}, sex = #{sex}, " +
            "rate = #{rate}, birth = #{birth}, address = #{address}, cardId = #{cardId}," +
            "tel = #{tel} where id = #{id};")
    void updateEmp(Employee emp);
    
    //    @Select("select id,name, passwd, birth, tel from emp_ms.employee where id = #{id} and passwd = #{passwd} ;")
    @Select("select e.id, e.name, e.tel, e.sex, e.address, e.rate, e.cardId, job.salary," +
            "e.dep_id, e.job_id, job.job_name, d.manager_id, d.dep_name, e.birth, e.passwd " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.id = #{id} and e.passwd = #{passwd};")
    Employee login(@Param("id") int id, @Param("passwd") String passwd);
    
    
    @Delete("delete from emp_ms.employee where id = #{id};")
    void deleteEmpById(int id);
    
}
