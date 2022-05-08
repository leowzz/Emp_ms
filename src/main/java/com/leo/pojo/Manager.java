package com.leo.pojo;

public class Manager extends People {
    private int id; // 管理员id
    private String name; // 管理员姓名
    private String passwd; // 管理员密码
    public Manager() {
    }
    
    public Manager(int id, String name, String passwd) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
    }
    
    public Manager(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }
    
    public Manager(String name) {
        this.name = name;
    }
    
    public String getType() {
        // 用户类型
        return "管理员";
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPasswd() {
        return passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + passwd + '\'' +
                '}';
    }
}
