package com.chenlf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenlf.entity.User;
import com.chenlf.vo.ResponseResult;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-06-28 21:54:15
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

