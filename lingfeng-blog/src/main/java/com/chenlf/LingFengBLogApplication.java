package com.chenlf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 
 * @author ChenLF
 * @date 2022/06/23 17:24
 **/

@SpringBootApplication
//@EnableConfigurationProperties(LingFengBLogApplication.class)
public class LingFengBLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LingFengBLogApplication.class, args);
    }
}
