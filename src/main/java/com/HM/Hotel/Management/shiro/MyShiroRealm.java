package com.HM.Hotel.Management.shiro;

import com.HM.Hotel.Management.Service.PwdService;
import com.HM.Hotel.Management.entity.Pwd;
import com.hazelcast.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rainmean.Li
 * @create 2019-06-13 10:37
 **/
public class MyShiroRealm extends AuthorizingRealm {

    //用于用户查询
    @Autowired
    private PwdService pwdService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        /*//获取令牌
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //从令牌中获取用户名
        String username = token.getUsername();
//按用户名查询用户
       Pwd user = pwdService.finByuser(username);
        if(user!=null){
            //获取用户的盐
            String salt = user.getSalt();
            ByteSource saltBytes = ByteSource.Util.bytes(salt);

            //创建简单的认证信息对象
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPwd(), saltBytes, this.getName());
            return authenticationInfo;
        }
        return null;*/
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
       Pwd user = pwdService.finByuser(token.getUsername());
        if (user != null ) {
            String salt = user.getSalt();
            ByteSource saltBytes = ByteSource.Util.bytes(salt);
            //创建简单的认证信息对象
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user != null ? user : null, user != null ? user.getPwd() : null, saltBytes, getName());
            return simpleAuthenticationInfo;
        }
        return null;

    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录用户
        Pwd currUser = (Pwd) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        Pwd user = pwdService.finByuser(currUser.getUserid());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
       /* System.out.println("角色数："+user.getRoles().size());
        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            System.out.println("权限数："+role.getPermissions().size());
            for (Permission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getMenuUrl());
                System.out.println(permission.getMenuUrl());
            }
        }*/
        return simpleAuthorizationInfo;
    }

}
