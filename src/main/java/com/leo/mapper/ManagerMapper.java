package com.leo.mapper;

import com.leo.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 管理员mapper
public interface ManagerMapper {
    /*查询所有部门信息, 包括: 部门编号, 部门名称, 部门主管编号, 部门主管姓名
    * 负责人: 王占泽 */
    @Select("select * from emp_ms.view_all_dep_info")
    List<HashMap<String,String>> selectAllDepInfo();
    
    // 负责人: 卢鹏飞
    @Select("select * from emp_ms.manager where name = #{name} and passwd = #{passwd}")
    Manager login(@Param("name")String name, @Param("passwd") String passwd);
    
    // 负责人: 卢鹏飞
    @Update("update emp_ms.manager set passwd = #{passwd} where name = #{name};")
    void changePasswd(@Param("name") String name, @Param("passwd") String passwd);
    
    /*修改部门的主管
    * 负责人: 卢鹏飞 */
    @Update("update emp_ms.department set manager_id = #{manager_id} where id = #{id};")
    void updateManagerOfDep(@Param("id") int dep_id, @Param("manager_id") int manager_id);
    
    /*通过部门编号查询部门的主管
    * 负责人: 卢鹏飞 */
    @Select("select manager_id from emp_ms.department where id = #{dep_id}")
    int selectManagerOfDep(int dep_id);
}
