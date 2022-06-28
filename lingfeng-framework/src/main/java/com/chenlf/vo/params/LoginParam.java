package com.chenlf.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录用户类
 * @author ChenLF
 * @date 2022/06/28 23:09
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam {
    private String userName;
    private String password;

}
