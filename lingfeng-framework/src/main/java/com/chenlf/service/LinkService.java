package com.chenlf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenlf.entity.Link;
import com.chenlf.vo.ResponseResult;

public interface LinkService extends IService<Link> {

    /**
     * 查询出所有的审核通过的友链。
     * @return
     */
    ResponseResult getAllLink();
}
