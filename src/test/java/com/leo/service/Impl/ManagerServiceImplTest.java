package com.leo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.leo.mapper.DepManagerMapper;
import com.leo.mapper.ManagerMapper;
import com.leo.pojo.Employee;
import com.leo.pojo.Manager;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ManagerServiceImplTest {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    @Test
    public void selectDepInfosTest() {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        List<HashMap<String, String>> maps = mapper.selectAllDepInfo();
        sqlSession.close();
        
        for (HashMap<String, String> map : maps) {
            for (String s : map.keySet()) {
//                System.out.println(s);
                Object o = map.get(s);
                if (o instanceof Integer)
                    System.out.print(o.toString() + " ");
                else
                    System.out.print(o + " ");
            }
            System.out.println("\n-----------------");
        }
    }
    
    @Test
    public void loginTest() {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        Manager m = mapper.login("leo", "031214");
        System.out.println(m);
        sqlSession.close();
    }
    
    @Test
    public void TestUser() {
        System.out.println(JSON.toJSONString(new user(12, "55")));
    }
    
    @Test
    public void TestBackup() throws IOException {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        managerService.backupDatabase();
    }
}


class user {
    int id;
    String name;
    
    public user(int id, String name) {
        this.id = id;
        this.name = name;
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
}