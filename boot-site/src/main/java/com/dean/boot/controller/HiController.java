package com.dean.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: luoxy
 * @Date: 2019/7/10
 * @Description:
 */
@RestController
public class HiController {

    @RequestMapping("/hello")
    public String greet(){
       return "hello world";
    }
}
