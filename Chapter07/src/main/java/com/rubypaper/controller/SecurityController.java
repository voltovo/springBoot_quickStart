package com.rubypaper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/")
    public String index(){
        System.out.println("index 요청.");
        return "index";
    }

    @GetMapping("/member")
    public void forMember(){
        System.out.println("member 요청.");
    }

    @GetMapping("/manager")
    public void forManager(){
        System.out.println("manager 요청.");
    }

    @GetMapping("/admin")
    public void forAdmin(){
        System.out.println("admin 요청.");
    }

    @GetMapping("/accessDenied")
    public void accessDenied(){
        System.out.println("accessDenied 요청.");
    }
}
