package com.leo.pojo;

import java.util.Scanner;

public class People {
    private Integer id;
    private String name;
    private String password;
    
    
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
    
    
//    public void setPassword(String passwd) {
//        Scanner scanner = new Scanner(System.in);
//        String re_enter = scanner.next();
//        if (re_enter.equals(passwd)) {
//            this.password = passwd;
//        } else
//            System.out.println("两次密码不一致, 设置失败");
//    }
    
}
