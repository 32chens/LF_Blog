package com.chenlf.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenlf.bos.LoginUserBo;
import com.chenlf.entity.User;
import com.chenlf.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
//import org.springframework.util.StringUtils;

/**
 * 
 * @author ChenLF
 * @date 2022/06/28 23:12
 **/

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名为空");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new RuntimeException("用户名不存在");
        }
        return new LoginUserBo(user);
    }
}
