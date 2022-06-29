package com.chenlf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenlf.entity.Comment;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.CommentPageParam;

public interface CommentService extends IService<Comment> {

    ResponseResult commentList(CommentPageParam commentPageParam);

    ResponseResult addComment(Comment comment);
}