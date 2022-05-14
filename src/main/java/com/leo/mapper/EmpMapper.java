package com.leo.mapper;

import com.leo.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmpMapper {
    
    /*通过编号查询员工信息
    * 负责人: 范明俊 */
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.id=#{id};")
    Employee selectInfoById(int id);
    
    /*通过姓名查询员工信息
     * 负责人: 范明俊 */
    @Select("select e.id, e.name, e.passwd, e.tel, e.sex, e.rate, e.address, " +
            "e.dep_id, e.job_id,e.birth, e.cardId, job.job_name, d.manager_id " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.name=#{name};")
    List<Employee> selectInfoByName(String name);
    
    @Select("select id from emp_ms.employee where name = #{name};")
    int selectIdByName(String name);
    
    /*新建员工, 形参为不带id的employee对象, 新建后自动将新建员工的编号赋值给employee对象
    * 负责人: 王占泽 */
    int createEmp(Employee emp);
    
    /*修改密码
    * 负责人: 范明俊 */
    @Update("update emp_ms.employee set passwd = #{passwd} where id = #{id};")
    void changePasswd(@Param("id") int id, @Param("passwd") String passwd);
    
    /*更新员工信息
    * 负责人: 王占泽 */
    @Update("update emp_ms.employee set name = #{name}, passwd = #{passwd}, sex = #{sex}, " +
            "rate = #{rate}, birth = #{birth}, address = #{address}, cardId = #{cardId}," +
            "tel = #{tel} where id = #{id};")
    void updateEmp(Employee emp);
    
    /*员工登录
    * 负责人: 王占泽 */
    @Select("select e.id, e.name, e.tel, e.sex, e.address, e.rate, e.cardId, job.salary," +
            "e.dep_id, e.job_id, job.job_name, d.manager_id, d.dep_name, e.birth, e.passwd " +
            "from emp_ms.employee as e, emp_ms.department as d, emp_ms.job where " +
            "e.dep_id = d.id and e.job_id = job.id and e.id = #{id} and e.passwd = #{passwd};")
    Employee login(@Param("id") int id, @Param("passwd") String passwd);
    
    /*通过编号删除员工
    * 负责人: 范明俊 */
    @Delete("delete from emp_ms.employee where id = #{id};")
    void deleteEmpById(int id);
    
}
