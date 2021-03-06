package com.chenlf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlf.constans.SystemConstants;
import com.chenlf.entity.Category;
import com.chenlf.mapper.ArticleMapper;
import com.chenlf.entity.Article;
import com.chenlf.mapper.CategoryMapper;
import com.chenlf.service.ArticleService;
import com.chenlf.utils.BeanCopyUtils;
import com.chenlf.vo.*;
import com.chenlf.vo.params.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @author ChenLF
 * @date 2022/06/23 18:38
 **/

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired(required = false)
    private ArticleMapper articleMapper;
    
    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> records = page.getRecords();
//        List<Object> hotVos = records.stream()
//                .map(article -> {
//                    HotArticleVo hotArticleVo = new HotArticleVo();
//                    BeanUtils.copyProperties(article,hotArticleVo);
//                    return hotArticleVo;
//                })
//                .collect(Collectors.toList());

        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(PageParam pageParam) {
//        ??????????????????????????????
//???	?????????????????????????????????????????????
        //???????????????????????????????????? ???????????????????????????????????????

        Integer pageNum = pageParam.getPageNum();
        Integer pageSize = pageParam.getPageSize();
        Long categoryId = pageParam.getCategoryId();

        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0, Article::getCategoryId,categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByAsc(Article::getIsTop);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();


        for (Article record : records) {
            Category category = categoryMapper.selectById(record.getCategoryId());
            record.setCategoryName(category.getName());
        }


        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(records, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //??????????????????????????????????????????
        Article article = articleMapper.selectById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryMapper.selectById(article.getCategoryId());
        if (category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }
}
