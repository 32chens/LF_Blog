package com.chenlf.service;

import com.chenlf.entity.User;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.LoginParam;

public interface BlogLoginService {
    ResponseResult login(LoginParam user);

    ResponseResult logout();
}