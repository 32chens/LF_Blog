package com.chenlf.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAop {

    @Pointcut("execution(* com.chenlf.service.*.*(..))")
    public void pt(){ }



    @Around("pt()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = pjp.getTarget().getClass().getName();

        log.info("=======Log Start=======");
//        // 打印请求 URL
//        log.info("URL            : {}",);
//        // 打印描述信息
//        log.info("BusinessName   : {}", );
//        // 打印 Http method
//        log.info("HTTP Method    : {}", );
//        // 打印调用 controller 的全路径以及执行方法
//        log.info("Class Method   : {}.{}", );
//        // 打印请求的 IP
//        log.info("IP             : {}",);
//        // 打印请求入参
//        log.info("Request Args   : {}",);
        Object o = pjp.proceed();
        // 打印出参
//        log.info("Response       : {}", );
        // 结束后换行
        log.info("=======End=======" + System.lineSeparator());
        return o;
    }

}
