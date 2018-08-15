package com.kevin.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class login {

    //@RequestMapping(value = "/user/login" ,method = RequestMethod.POST) 两种不同的写法
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        if (!StringUtils.isEmpty(username)&&password.equals("123456")){
            //登录成功传一个session
            session.setAttribute("user", username);
            return "redirect:/main.html";
        }
        else{
            model.addAttribute("msg","用户名密码错误");
            return "index";
        }

    }
}
