package com.xiejiadao.girl.controller;

import com.xiejiadao.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * hello springboot
 * @author: xyf
 * @date: 2019/4/13 23:36
 */
@RestController
@RequestMapping(("/say"))
//@Controller
public class HelloController {

    @Autowired
    GirlProperties girlProperties;

    @RequestMapping(value = {"/hello","/hi/{id}"}, method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id, @RequestParam(value = "name", required = false,defaultValue = "lili") String myName) {
        String girl = girlProperties.getCupSize() + girlProperties.getAge();
        return girlProperties.getCupSize() + myName + id;
    }
}
