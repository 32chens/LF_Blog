package com.chenlf.controller;

import com.chenlf.entity.Comment;
import com.chenlf.service.CommentService;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.PageParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(PageParam pageParam){
        return commentService.commentList(pageParam);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}