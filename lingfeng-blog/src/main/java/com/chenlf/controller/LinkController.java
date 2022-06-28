package com.chenlf.controller;

import com.chenlf.service.LinkService;
import com.chenlf.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
