package com.HM.Hotel.Management.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebComponent2Config {

    @Bean
    public FilterRegistrationBean loginSessionFilter() {//这个类应该是Spring给我们拿来初测filter的

        FilterRegistrationBean registration = new FilterRegistrationBean();//新建过滤器的注册类

        registration.setFilter(new AdminLoginValidationFilter());//添加我们写好的filter

        registration.addUrlPatterns("");//设置filter的过滤的url模式

        return registration;//返回这个就是Spring会帮你注入的那个对象

    }
}
