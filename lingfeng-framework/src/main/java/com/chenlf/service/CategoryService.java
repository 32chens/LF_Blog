package com.chenlf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenlf.entity.Category;
import com.chenlf.vo.ResponseResult;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-06-27 21:27:21
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

