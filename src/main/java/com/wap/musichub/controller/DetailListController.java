package com.wap.musichub.controller;

import com.wap.musichub.dto.DetailListDto;
import com.wap.musichub.service.DetailListService;
import com.wap.musichub.service.PlaylistService;
import com.wap.musichub.service.RequestListService;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class DetailListController {

    private DetailListService detailListService;
    private RequestListService requestListService;

    @Autowired
    public DetailListController(DetailListService detailListService, RequestListService requestListService) {
        this.detailListService = detailListService;
        this.requestListService = requestListService;
    }

    @PostMapping("/detailList/add")
    public String addList(DetailListDto detailListDto) {

        Long id = detailListService.saveList(detailListDto);

        requestListService.deleteById(detailListDto.getRequestId());

        return "redirect:/member/management/"+detailListDto.getPostId();
    }


    // requestList 삭제
    @DeleteMapping("/detailList/delete")
    public String delete(DetailListDto detailListDto) {
        requestListService.deleteById(detailListDto.getRequestId());

        return "redirect:/member/management/"+detailListDto.getPostId();
    }

    // detailList 삭제
    @DeleteMapping("/playlist/delete/{id}")
    public String deleteFromList(@PathVariable("id") Long id, DetailListDto detailListDto) {
        detailListService.deleteById(id);

        return "redirect:/playlist/" + detailListDto.getPostId();
    }
}
