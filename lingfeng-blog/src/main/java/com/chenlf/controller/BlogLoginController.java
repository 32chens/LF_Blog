package com.chenlf.controller;

import com.chenlf.entity.User;
import com.chenlf.enums.AppHttpCodeEnum;
import com.chenlf.exception.SystemException;
import com.chenlf.service.BlogLoginService;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginParam user){
        if (user.getUserName() == null){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
}
