package com.wap.musichub.controller;

import com.wap.musichub.dto.DetailListDto;
import com.wap.musichub.dto.PlaylistDto;
import com.wap.musichub.dto.RequestListDto;
import com.wap.musichub.service.DetailListService;
import com.wap.musichub.service.PlaylistService;
import com.wap.musichub.service.RequestListService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PlaylistController {

    private PlaylistService playlistService;
    private RequestListService requestListService;
    private DetailListService detailListService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, RequestListService requestListService, DetailListService detailListService) {
        this.playlistService = playlistService;
        this.requestListService = requestListService;
        this.detailListService = detailListService;
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

        List<DetailListDto> detailDtoList = detailListService.getListByPostId(id);
        model.addAttribute("detailDtoList", detailDtoList);

        return "playlist/detail";
    }

    @PostMapping("/playlist/request")
    public String request(RequestListDto requestListDto) throws IOException{

        String youtube_id = getYouTubeId(requestListDto.getLink());

        String result_url = "https://noembed.com/embed?url=https://www.youtube.com/watch?v=" + youtube_id;

        JSONObject json = readJsonFromUrl(result_url);
        System.out.println(json.toString());
        System.out.println(json.get("title"));

        String title = (String) json.get("title");

        requestListDto.setTitle(title);

        System.out.println(requestListDto.getPostId());
        System.out.println(requestListDto.getLink());
        System.out.println(requestListDto.getTitle());

        requestListService.saveRequestList(requestListDto);

        return "redirect:/playlist/" + requestListDto.getPostId();
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    private static String getYouTubeId(String youTubeUrl) {
        String pattern = "https?://(?:[0-9A-Z-]+\\.)?(?:youtu\\.be/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|</a>))[?=&+%\\w]*";

        Pattern compiledPattern = Pattern.compile(pattern,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
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

        List<RequestListDto> requestListDtos = requestListService.getListByPostId(id);

        model.addAttribute("playlistDto", dto);
        model.addAttribute("requestDto", requestListDtos);

        return "/member/management";

    }
}
