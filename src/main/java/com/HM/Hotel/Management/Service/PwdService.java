package com.HM.Hotel.Management.Service;

import com.HM.Hotel.Management.entity.Pwd;

import java.util.List;
import java.util.Map;

public interface PwdService {

    Pwd save(Pwd pwd);
    void delete(int id);
    Pwd finById(int id);
    List<Pwd> finAll();
    Pwd finByuser(String user);

    Map<String, Object> register(String username, String password); //用户密码权限
    Map<String, Object> login(String username, String password);//登录


}
