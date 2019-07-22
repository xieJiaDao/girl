package com.xiejiadao.girl.controller;

import com.xiejiadao.girl.annotations.AdminOnly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试并发
 * @author: xyf
 * @date: 2019/7/14 17:41
 */
@RestController
//@RequestMapping("/test")
@Slf4j
public class TestController {

    @AdminOnly
    @GetMapping("/test")
    public String test() {
//        log.info("a");
        return "test";
    }
}
