package com.wap.musichub.controller;

import com.wap.musichub.dto.DetailListDto;
import com.wap.musichub.service.DetailListService;
import com.wap.musichub.service.PlaylistService;
import com.wap.musichub.service.RequestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String addList(DetailListDto detailListDto){

        Long id = detailListService.saveList(detailListDto);

        requestListService.deleteById(detailListDto.getRequestId());

        return "redirect:/member/management/"+detailListDto.getPostId();
    }

    @DeleteMapping("/detailList/delete")
    public String delete(DetailListDto detailListDto) {
        requestListService.deleteById(detailListDto.getRequestId());

        return "redirect:/member/management/"+detailListDto.getPostId();
    }
}
