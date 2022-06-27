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
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId) {
//        首页：查询所有的文章
//​	分类页面：查询对应分类下的文章
        //①只能查询正式发布的文章 ②置顶的文章要显示在最前面
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0, Article::getCategoryId,categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByAsc(Article::getIsTop);
        page(page, queryWrapper);
        List<Article> records = page.getRecords();

        List<Category> categories = categoryMapper.selectList(null);
        for (Article record : records) {
//            String categoryName = 
            Optional<Category> first = categories.stream()
                    .filter(category -> categoryId.equals(record.getCategoryId()))
                    .findFirst();
            Category category = first.orElseThrow(() ->new RuntimeException("文章的分类id错误"));
            record.setCategoryName(category.getName());
        }


        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(records, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //①要在文章详情中展示其分类名
        Article article = articleMapper.selectById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryMapper.selectById(article.getCategoryId());
        if (category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }
}
