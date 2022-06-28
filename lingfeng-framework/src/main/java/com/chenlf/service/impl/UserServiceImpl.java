package com.chenlf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlf.entity.User;
import com.chenlf.mapper.UserMapper;
import com.chenlf.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-06-28 21:54:15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

