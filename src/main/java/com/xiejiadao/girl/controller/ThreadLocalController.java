package com.xiejiadao.girl.controller;

import com.xiejiadao.girl.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证ThreadLocal配合filter Interceptor
 * 实现保存请求信息。。
 */
@Slf4j
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    /**
     * 通过RequestHolder中的成员变量ThreadLocal 获取保存的线程ID
     *
     * 实际中可以保存用户信息，以便于在项目中去获取
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "执行本次请求的线程id是：" + RequestHolder.getId();
    }

}
