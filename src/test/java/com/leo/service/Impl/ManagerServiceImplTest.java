package com.leo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.leo.mapper.ManagerMapper;
import com.leo.pojo.Manager;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.junit.Test;

import java.util.List;
import java.util.HashMap;
import java.io.IOException;

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
    public void TestBackup() throws IOException {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        managerService.backupDatabase();
    }
    
    @Test
    public void selectAllDepInfo() {
    }
    
    @Test
    public void login() {
    }
    
    @Test
    public void changePasswd() {
    }
    
    @Test
    public void updateManagerOfDep() {
    }
    
    @Test
    public void selectManagerIdOfDep() {
    }
    
    @Test
    public void get_time() {
    }
    
    @Test
    public void backupDatabase() {
    }
    
}

