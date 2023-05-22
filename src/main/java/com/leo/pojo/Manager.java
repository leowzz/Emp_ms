package com.leo.pojo;

public class Manager extends User {
    private Integer id; // 管理员id
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
    
    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + passwd + '\'' +
                '}';
    }
}
