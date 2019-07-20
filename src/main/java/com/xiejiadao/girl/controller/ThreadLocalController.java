package com.xiejiadao.girl.controller;

import com.xiejiadao.girl.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证ThreadLocal配合filter Interceptor
 * 实现保存请求信息。。
 */
@Slf4j
@RestController("/threadLocal")
public class ThreadLocalController {

    /**
     *
     * @return
     */
    @GetMapping("/test")
    public Long test() {
        return RequestHolder.getId();
    }

}
