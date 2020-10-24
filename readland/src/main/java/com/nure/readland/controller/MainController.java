package com.nure.readland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/admin")
    public String adminMain(){
        return "adminMain";
    }

    @GetMapping("/user")
    public String userMain(){
        return "userMain";
    }

    @GetMapping("/lib")
    public String libMain(){
        return "libMain";
    }
}
