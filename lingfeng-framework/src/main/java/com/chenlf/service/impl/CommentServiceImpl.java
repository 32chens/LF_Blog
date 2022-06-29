package com.chenlf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlf.entity.Comment;
import com.chenlf.entity.User;
import com.chenlf.enums.AppHttpCodeEnum;
import com.chenlf.exception.SystemException;
import com.chenlf.mapper.CommentMapper;
import com.chenlf.mapper.UserMapper;
import com.chenlf.service.CommentService;
import com.chenlf.service.UserService;
import com.chenlf.utils.BeanCopyUtils;
import com.chenlf.utils.SecurityUtils;
import com.chenlf.vo.CommentVo;
import com.chenlf.vo.PageVo;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.CommentPageParam;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;

/**
 * 
 * @author ChenLF
 * @date 2022/06/29 16:04
 **/

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    public UserMapper userMapper;

    @Override
    public ResponseResult commentList(CommentPageParam commentPageParam) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, commentPageParam.getArticleId());
        queryWrapper.orderByAsc(Comment::getCreateTime,Comment::getId);

//        Page<Comment> commentPage = new Page<>(commentPageParam.getPageNum(), commentPageParam.getPageSize());
//        page(commentPage,queryWrapper);


//        List<Comment> records = commentPage.getRecords();
        List<Comment> records = list(queryWrapper);
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(records, CommentVo.class);

        List<CommentVo> rootComments = commentVos.stream()
                .filter(commentVo -> commentVo.getRootId() == -1)
                .map(commentVo -> {
//                    User toUser = userMapper.selectById(commentVo.getToCommentUserId());
//                    commentVo.setToCommentUserName(toUser.getNickName());
                    User user = userMapper.selectById(commentVo.getCreateBy());
                    commentVo.setUsername(user.getNickName());
                    return commentVo;
                })
                .collect(Collectors.toList());

        List<CommentVo> childComemetns = commentVos.stream()
                .filter(commentVo -> commentVo.getRootId() != -1)
                .map(commentVo -> {
                    User toUser = userMapper.selectById(commentVo.getToCommentUserId());
                    commentVo.setToCommentUserName(toUser.getNickName());
                    User user = userMapper.selectById(commentVo.getCreateBy());
                    commentVo.setUsername(user.getNickName());
                    return commentVo;
                })
                .collect(Collectors.toList());

        for (CommentVo rootComment : rootComments) {
            List<CommentVo> collect = childComemetns.stream()
                    .filter(commentVo -> commentVo.getToCommentId().equals(rootComment.getId()))
                    .collect(Collectors.toList());
            rootComment.setChildren(collect);
        }

        return ResponseResult.okResult(new PageVo(rootComments, commentPageParam.getPageSize().longValue()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        comment.setCreateBy(SecurityUtils.getUserId());
        boolean save = save(comment);
        return ResponseResult.okResult();
    }
}
