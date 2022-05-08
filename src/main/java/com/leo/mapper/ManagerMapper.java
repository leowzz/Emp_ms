package com.leo.mapper;

import com.leo.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    @Select("select * from emp_ms.view_all_dep_info")
    List<HashMap<String,String>> selectAllDepInfo();
    
    @Select("select * from emp_ms.manager where name = #{name} and passwd = #{passwd}")
    Manager login(@Param("name")String name, @Param("passwd") String passwd);
    
    @Update("update emp_ms.manager set passwd = #{passwd} where name = #{name};")
    void changePasswd(@Param("name") String name, @Param("passwd") String passwd);
}
