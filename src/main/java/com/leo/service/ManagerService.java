package com.leo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<HashMap<String, String>> selectAllDepInfo();
    
    boolean login(String id, String passwd);
    
    boolean changePasswd(String name, String passwd);
    
    boolean updateManagerOfDep(int dep_id, int manager_id);
    
    boolean backupDatabase() throws IOException;
}
