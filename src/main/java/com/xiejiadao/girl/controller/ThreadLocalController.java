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
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "执行本次请求的线程id是：" + RequestHolder.getId();
    }

}
