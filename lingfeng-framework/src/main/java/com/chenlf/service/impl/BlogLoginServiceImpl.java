package com.chenlf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenlf.bos.LoginUserBo;
import com.chenlf.entity.User;
import com.chenlf.service.BlogLoginService;
import com.chenlf.utils.BeanCopyUtils;
import com.chenlf.utils.JwtUtil;
import com.chenlf.utils.RedisCache;
import com.chenlf.vo.BlogUserLoginVo;
import com.chenlf.vo.ResponseResult;
import com.chenlf.vo.UserInfoVo;
import com.chenlf.vo.params.LoginParam;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @author ChenLF
 * @date 2022/06/28 23:05
 **/

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(LoginParam user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
            throw new RuntimeException("用户名或密码为空");
        }
        //认证
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (authenticate == null){
            throw new RuntimeException("用户名密码错误");
        }

        //成功 生成jwt 放入redis
        LoginUserBo principal = (LoginUserBo) authenticate.getPrincipal();
        String userId = principal.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        redisCache.setCacheObject("Token_"+userId,principal);

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(principal.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserBo principal = (LoginUserBo) authentication.getPrincipal();
        String userId = principal.getUser().getId().toString();
        redisCache.deleteObject("Token_"+userId);
        return ResponseResult.okResult();
    }
}
