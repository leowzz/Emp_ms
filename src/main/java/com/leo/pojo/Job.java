package com.leo.pojo;

public class Job {
    private Integer id;
    private String job_name;
    private Integer salary;
    private Integer dep_id;
    
    public Job() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getJob_name() {
        return job_name;
    }
    
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    
    public Integer getSalary() {
        return salary;
    }
    
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    public Integer getDep_id() {
        return dep_id;
    }
    
    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }
}
