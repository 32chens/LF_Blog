package com.chenlf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenlf.entity.Article;
import com.chenlf.vo.ResponseResult;

/**
 * 
 * @author ChenLF
 * @date 2022/06/23 18:38
 **/
 
public interface ArticleService extends IService<Article> {
    /**
     * 查询最热文章信息
     * @return
     */
    ResponseResult hotArticleList();

    /**
     * 文章列表
     * @return
     */
    ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId);
}
