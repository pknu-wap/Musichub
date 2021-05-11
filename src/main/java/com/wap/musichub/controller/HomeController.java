package com.wap.musichub.controller;

// 메인화면( 플레이 리스트 목록 )의 컨트롤러

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "playlist/home";
    }
}
