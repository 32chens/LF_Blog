package com.chenlf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenlf.entity.User;
import com.chenlf.mapper.UserMapper;
import com.chenlf.service.UserService;
import com.chenlf.utils.BeanCopyUtils;
import com.chenlf.utils.SecurityUtils;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.UserInfoVo;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-06-28 21:54:15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }
}

