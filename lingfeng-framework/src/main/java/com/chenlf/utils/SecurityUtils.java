package com.chenlf.utils;

import com.chenlf.bos.LoginUserBo;
import com.chenlf.constans.SystemConstants;
import com.chenlf.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.chenlf.constans.SystemConstants.USER_TYPE;

public class SecurityUtils
{

    /**
     * 获取用户
     **/
    public static LoginUserBo getLoginUser()
    {
        return (LoginUserBo) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        User user = getLoginUser().getUser();
        Long id = getLoginUser().getUser().getId();
        return id != null && user.getType() == SystemConstants.USER_TYPE;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}