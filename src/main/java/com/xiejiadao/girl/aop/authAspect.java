package com.xiejiadao.girl.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 */
@Slf4j
@Aspect
@Component
public class authAspect {

    @Pointcut("@annotation(com.xiejiadao.girl.annotations.AdminOnly)")
    public void adminOnly() {

    }

    @Before("adminOnly()")
    public void before() {
        log.info("执行aop，before方法。");
    }

}
