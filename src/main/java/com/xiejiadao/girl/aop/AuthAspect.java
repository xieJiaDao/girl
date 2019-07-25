package com.xiejiadao.girl.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *切面demo
 */
@Slf4j
@Aspect
@Component
public class AuthAspect {

    @Pointcut("@annotation(com.xiejiadao.girl.annotations.AdminOnly)")
    public void adminOnly() {

    }

    @Before("adminOnly()")
    public void before() {
        log.info("执行aop，before方法。");
    }

}
