package com.chenlf.controller;

import com.chenlf.entity.Comment;
import com.chenlf.service.CommentService;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.CommentPageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(CommentPageParam commentPageParam){
        return commentService.commentList(commentPageParam);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}