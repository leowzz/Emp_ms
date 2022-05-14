package com.leo.service.Impl;

import com.leo.mapper.EmpMapper;
import com.leo.mapper.ManagerMapper;
import com.leo.pojo.Employee;
import com.leo.pojo.Manager;
import com.leo.service.ManagerService;
import com.leo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.io.File.separator;

// 管理员service实现类
public class ManagerServiceImpl implements ManagerService {
    // 创建工厂对象, 以便获取mapper对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    
    // 查询所有部门信息方法, 包括: 部门编号, 部门名称, 部门主管编号, 部门主管姓名
    // 负责人: 王占泽
    public List<HashMap<String, String>> selectAllDepInfo() {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        List<HashMap<String, String>> maps = mapper.selectAllDepInfo();
        sqlSession.close();
        return maps;
    }
    
    // 管理员登录方法, 登录成功返回true, 失败返回false
    // 负责人: 卢鹏飞
    public boolean login(String name, String passwd) {
        // 获取sqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 获取mapper对象
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        // 调用方法
        Manager m = mapper.login(name, passwd);
        // 关闭sqlSession
        sqlSession.close();
        // 判断是否为空
        return m != null;
    }
    
    // 管理员修改密码方法
    // 负责人: 卢鹏飞
    public boolean changePasswd(String name, String passwd) {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        mapper.changePasswd(name, passwd);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
    
    // 更新部门的主管
    // 负责人: 卢鹏飞
    public boolean updateManagerOfDep(int dep_id, int manager_id) {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        // 首先查询出要更改为主管的员工是否存在
        Employee emp = empMapper.selectInfoById(manager_id);
        if (emp == null) {
            sqlSession.close();
            return false; // 员工不存在, 直接返回false
        }
        // 员工存在, 更新部门主管
        mapper.updateManagerOfDep(dep_id, manager_id);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
    
    // 根据部门编号查询部门主管编号
    // 负责人: 卢鹏飞
    public int selectManagerIdOfDep(int dep_id) {
        SqlSession sqlSession = factory.openSession();
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        int manager_id = mapper.selectManagerOfDep(dep_id);
        sqlSession.close();
        return manager_id;
    }
    
    // 获取格式化时间静态方法, 供备份数据库时使用
    public static String get_time() throws IOException {
        return new SimpleDateFormat("yyyyMMdd_HH_mm").format(new Date());
    }
    
    // 备份数据库方法
    // 负责人: 王占泽
    public boolean backupDatabase() throws IOException {
        String absPath = new File("").getCanonicalPath(); // 获取项目的绝对路径
        String savePath = absPath + separator + "bak\\databaseBacks\\"; // 文件备份路径
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            boolean f = saveFile.mkdirs();// 创建文件夹
            System.out.println("目录不存在, 创建文件夹" + absPath + separator + savePath + "成功：" + f);
        }
        if (!savePath.endsWith(separator)) {
            savePath = savePath + separator;
        }
        try {
            String fileName = get_time() + ".txt";
            String command = "cmd /c mysqldump -u leo -p031214 --set-charset=UTF8 emp_ms > " + savePath + fileName;
            Process process = Runtime.getRuntime().exec(command);
            if (process.waitFor() == 0) {
                // 0 表示线程正常终止。
                System.out.println("备份成功, 备份文件路径为：\n" + savePath + fileName);
                return true;
            } else {
                System.out.println("备份失败");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
