package com.leo.service.Impl;

import com.leo.mapper.EmpMapper;
import com.leo.mapper.ManagerMapper;
import com.leo.pojo.Employee;
import com.leo.pojo.Manager;
import com.leo.service.ManagerService;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerServiceImpl implements ManagerService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    
    public List<HashMap<String, String>> selectAllDepInfo() {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        List<HashMap<String, String>> maps = mapper.selectAllDepInfo();
        sqlSession.close();
        return maps;
    }
    
    public boolean login(String name, String passwd) {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        Manager m = mapper.login(name, passwd);
        sqlSession.close();
        return m != null;
    }
    
    public boolean changePasswd(String name, String passwd) {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        mapper.changePasswd(name, passwd);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
