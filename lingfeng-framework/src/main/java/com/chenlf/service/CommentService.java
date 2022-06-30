package com.chenlf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenlf.entity.Comment;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.PageParam;

public interface CommentService extends IService<Comment> {

    ResponseResult commentList(PageParam pageParam);

    ResponseResult addComment(Comment comment);
}