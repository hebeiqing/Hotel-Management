package com.HM.Hotel.Management.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Rainmean.Li
 * @create 2019-06-13 10:47
 **/
@Configuration
public class ShiroConfiguration {

    @Bean
    public MyShiroRealm myRealm() {
    return new MyShiroRealm();
}

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm()); //
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
       /* shiroFilterFactoryBean.setLoginUrl("/login");//登录链接
        shiroFilterFactoryBean.setSuccessUrl("/");//登录成功链接
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/500");//认证不通过跳转

        Map<String,String> map = new HashMap<String, String>();
        map.put("/","anon");
        map.put("/login","anon");

        map.put("/error/**","anon");
        //登出
        map.put("/logout","logout");
        //对所有用户认证
        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);*/

        return shiroFilterFactoryBean;
    }

    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        System.out.println("开启了Shiro注解支持");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException", "/error/500");  //捕捉权限异常跳转403页面
        exceptionResolver.setExceptionMappings(mappings);  // None by default
        exceptionResolver.setDefaultErrorView("error");    // No default
        exceptionResolver.setExceptionAttribute("ex");     // Default is "exception" //页面上获取异常信息变量名
        return exceptionResolver;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());// 配置 加密 （在加密后，不配置的话会导致登陆密码失败）
        System.out.println("myShiroRealm 注入成功");
        return myShiroRealm;
    }

    /*加盐加密设置*/
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5"); // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashIterations(1024); // 设置散列次数： 意为加密几次
        System.out.println("hashedCredentialsMatcher");
        return hashedCredentialsMatcher;
    }

    /*生命周期*/
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
