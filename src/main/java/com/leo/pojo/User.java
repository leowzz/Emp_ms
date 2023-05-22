package com.leo.pojo;

public class User {
    private Integer id=-1;
    private String name;
    private String passwd;
    
    public int getId() {
        return id;
    }
    
    public void setId(Integer id) {
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
    
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + passwd + '\'' +
                '}';
    }
}
