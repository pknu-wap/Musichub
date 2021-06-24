package com.wap.musichub.controller;

import com.wap.musichub.dto.DetailListDto;
import com.wap.musichub.service.DetailListService;
import com.wap.musichub.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DetailListController {

    private DetailListService detailListService;

    @Autowired
    public DetailListController(DetailListService detailListService) {
        this.detailListService = detailListService;
    }

    @PostMapping("/detailList/add")
    public String addList(DetailListDto detailListDto){

        Long id = detailListService.saveList(detailListDto);

        return "redirect:/member/management/"+detailListDto.getPostId();
    }
}
