package com.rubypaper.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/system/login")
    public void login(){
        System.out.println("로그인 페이지 요청");
    }

    @GetMapping("/syste/accessDenied")
    public void accessDenied(){
        System.out.println("접근 거부 페이지 요청");
    }

    @GetMapping("/system/logout")
    public void logout(){
        System.out.println("로그아웃 페이지 요청");
    }

    @GetMapping("/admin/adminPage")
    public void admin(){
        System.out.println("관리자 페이지 요청");
    }
}
