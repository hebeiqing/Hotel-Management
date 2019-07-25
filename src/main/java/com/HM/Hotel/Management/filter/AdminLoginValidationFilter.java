package com.HM.Hotel.Management.filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//登录验证
@Configuration
@WebFilter(filterName = "AdminLoginValidationFilter",urlPatterns = {"/stayin","/checkout","/adminlist","/dateist","/roomInfoList","/roomtypelist","/customerlist","/orderlist","/livinglist"})
public class AdminLoginValidationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("管理员登录验证过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=( HttpServletRequest)servletRequest;
        HttpServletResponse response= (HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        if (session.getAttribute("loginUser")!=null){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"login");
        }
    }

    @Override
    public void destroy() {
        System.out.println("管理员登录验证过滤器已销毁...");
    }



}
