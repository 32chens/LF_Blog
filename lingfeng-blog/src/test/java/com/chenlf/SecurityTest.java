package com.chenlf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * 
 * @author ChenLF
 * @date 2022/06/29 00:05
 **/

@SpringBootTest
public class SecurityTest {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testBCryptPasswordEncoder(){
        String encode = bCryptPasswordEncoder.encode("1234");
        boolean matches = bCryptPasswordEncoder.matches("1234", "$2a$10$21D7QKwcwFJzwZUU5EjO1OXjb06w7/3JlHY6tVH6kHeLrjTE6V.Gi");
        System.out.println(matches);
    }
}
