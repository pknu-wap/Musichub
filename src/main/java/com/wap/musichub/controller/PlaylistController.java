package com.wap.musichub.controller;

import com.wap.musichub.dto.PlaylistDto;
import com.wap.musichub.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaylistController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
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
