package com.wap.musichub.controller;

import com.wap.musichub.dto.PlaylistDto;
import com.wap.musichub.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PlaylistController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlist")
    public String create(Model model) {

        // 글 쓸 때, 작성자 이름 미리 지정
        // 타임리프에서 어떻게 가져올지 몰라서, authorities Arraylist에서 추출해서 사용함.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nickname = ""+new ArrayList(auth.getAuthorities()).get(1);
        model.addAttribute("nickname", nickname);

        return "playlist/create";
    }

    @PostMapping("/playlist")
    public String create(PlaylistDto playlistDto) {

        playlistService.savePlaylist(playlistDto);

        return "redirect:/";
    }

    @GetMapping("/playlist/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        PlaylistDto dto = playlistService.getById(id);

        model.addAttribute("playlistDto", dto);

        return "playlist/detail";
    }

    @DeleteMapping("/playlist/{id}")
    public String delete(@PathVariable("id") Long id) {

        playlistService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/playlist/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        PlaylistDto dto = playlistService.getById(id);

        model.addAttribute("playlistDto", dto);
        return "playlist/edit";
    }

    @PutMapping("/playlist/edit/{id}")
    public String update(@PathVariable("id") Long id, PlaylistDto playlistDto) {
        playlistService.savePlaylist(playlistDto);

        return "redirect:/playlist/" + id;
    }
}
