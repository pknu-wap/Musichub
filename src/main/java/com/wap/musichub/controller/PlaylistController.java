package com.wap.musichub.controller;

import com.wap.musichub.dto.PlaylistDto;
import com.wap.musichub.dto.RequestListDto;
import com.wap.musichub.service.PlaylistService;
import com.wap.musichub.service.RequestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaylistController {

    private PlaylistService playlistService;
    private RequestListService requestListService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, RequestListService requestListService) {
        this.playlistService = playlistService;
        this.requestListService = requestListService;
    }

    @GetMapping("/playlist")
    public String create() {

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

    @PostMapping("/playlist/request")
    public String request(RequestListDto requestListDto) {

        requestListService.saveRequestList(requestListDto);

        return "redirect:/playlist/" + requestListDto.getPostId();
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

    // my page
    @GetMapping("/member/info")
    public String displayInfo(Model model){


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<PlaylistDto> playDtoList = playlistService.getByWriter(auth.getName());

        model.addAttribute("playDtoList", playDtoList);

        return "/member/info";
    }

    // management page
    @GetMapping("/member/management/{id}")
    public String managePlaylist(@PathVariable("id") Long id, Model model) {
        PlaylistDto dto = playlistService.getById(id);

        model.addAttribute("playlistDto", dto);

        return "/member/management";

    }
}
