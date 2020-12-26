package com.nure.readland.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUser(Authentication authentication){
//        return authentication.getName();
//    }
}
