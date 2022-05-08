package com.leo.pojo;

import java.util.Vector;

public class Department {
    private int id; //部门id
    private String dep_name; //部门名称: 财政部 人事部 市场部 生产部 信息部
    private String manager_id; //部门描述
    private String manager; //部门经理
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDep_name() {
        return dep_name;
    }
    
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    
    public String getManager_id() {
        return manager_id;
    }
    
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
    
    public String getManager() {
        return manager;
    }
    
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dep_name='" + dep_name + '\'' +
                ", manager_id='" + manager_id + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}


