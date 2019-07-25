package com.HM.Hotel.Management.controller;

import com.HM.Hotel.Management.Service.PwdService;
import com.HM.Hotel.Management.entity.Pwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Configuration
public class adminController {
    @Autowired
    private PwdService pwdService;

    @RequestMapping("adminlist")
    public String list(Model model){
        List<Pwd> lsit=pwdService.finAll();
        model.addAttribute("admin",lsit);
        return "admin/admin";
    }

   /* @GetMapping("register")
    public String register(){
        return "login/login";
    }
*/
    @GetMapping("login")
    public String login(){
        return "login";
    }

   /* @PostMapping("register")
    public String register(String username, String password,Map<String, Object> map, RedirectAttributes redirectAttributes){
        Map<String, Object> resultMap = pwdService.register(username,password);
        if((Boolean) resultMap.get("ok")){
            redirectAttributes.addFlashAttribute("message", "注册成功！请登录！");
            return "redirect:login";
        }else{
            map.put("error", resultMap.get("error"));
            return "register";
        }
    }*/

    @PostMapping("login")
    public String login(String username, String password, HttpSession session, Model model){
        Map<String, Object> resultMap = pwdService.login(username, password);
        if((Boolean) resultMap.get("ok")){
            //把登录成功后的用户对象保存到session中
            System.out.println("登录成功");
            session.setAttribute("loginUser", resultMap.get("user"));
            return "redirect:dateist";
        }else{
            model.addAttribute("error", resultMap.get("error"));
            return "login";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        System.out.println("退出登陆成功");
        return "login";
    }



}
