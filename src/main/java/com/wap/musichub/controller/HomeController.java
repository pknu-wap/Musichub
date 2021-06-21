package com.wap.musichub.controller;

// 메인화면( 플레이 리스트 목록 )의 컨트롤러

import com.wap.musichub.dto.PlaylistDto;
import com.wap.musichub.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private PlaylistService playlistService;

    @Autowired
    public HomeController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<PlaylistDto> playlistList = playlistService.getList();
        model.addAttribute("playlistList", playlistList);

        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<PlaylistDto> playlistList = playlistService.searchPlaylist(keyword);

        model.addAttribute("playlistList", playlistList);

        return "home";
    }
}
