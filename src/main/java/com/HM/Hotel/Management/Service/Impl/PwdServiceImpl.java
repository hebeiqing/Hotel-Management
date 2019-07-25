package com.HM.Hotel.Management.Service.Impl;

import com.HM.Hotel.Management.Service.PwdService;
import com.HM.Hotel.Management.dao.PwdRepository;
import com.HM.Hotel.Management.entity.Pwd;
import com.HM.Hotel.Management.shiro.ShiroUtil;
import com.hazelcast.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PwdServiceImpl implements PwdService {

    @Autowired
    private PwdRepository pwdRepository;
    @Override
    public Pwd save(Pwd pwd) {
        return pwdRepository.save(pwd);
    }

    @Override
    public void delete(int id) {
      pwdRepository.deleteById(id);
    }

    @Override
    public Pwd finById(int id) {
        return pwdRepository.findById(id).get();
    }

    @Override
    public List<Pwd> finAll() {
        return (List<Pwd>) pwdRepository.findAll();
    }


    public Pwd finByuser(String user) {
        return pwdRepository.findByUserid(user);
    }


    @Override   //注册
    public Map<String, Object> register(String username, String password) {

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("ok",false);

        Pwd user = pwdRepository.findByUserid(username);
        if(user==null){
            user = new Pwd();
            user.setUserid(username);

            //加盐加密
            String salt = ShiroUtil.createSalt();
            String passwordBySalt = ShiroUtil.createPwdBySalt(password, salt);
            user.setPwd(passwordBySalt);
            user.setSalt(salt);

           pwdRepository.save(user);
            if(user.getId()!=null){
                map.put("ok",true);
            }else{
                map.put("error","注册失败！");
            }
        }else{
            map.put("error","用户名已被注册！");
        }
        return map;
    }


    @Override  //登录
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("ok",false);
        //使用shiro的认证
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);
            map.put("ok",true);
            //从subject中获取当前登录用户的对象
           Pwd user = (Pwd) subject.getPrincipal();
            map.put("user",user);
        }catch (UnknownAccountException e){
            e.printStackTrace();
            map.put("error","登录失败！用户不存在！");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            map.put("error","登录失败！密码错误！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","登录失败！内部错误！");
        }
        return map;
    }

}
