package com.ypdaic.mymall.product.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.ypdaic.mymall.product.service.impl.HystrixServiceImpl;
import com.ypdaic.mymall.product.service.impl.Test;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @ClassName HystrixController
 * @Description TODO
 * @Author daiyanping
 * @Date 2020/6/24
 * @Version 0.1
 */
@Controller
@RequestMapping
public class HystrixController {

    @Autowired
    HystrixServiceImpl hystrixService;

    @Autowired
    Test test;

    @ResponseBody
    @GetMapping("/test/hystrix")
    public String test() {
//        hystrixService.test();
        // 清空缓存
//        hystrixService.test2();
//        hystrixService.test();
//        hystrixService.test4();
        return hystrixService.test();
    }

    @ResponseBody
    @GetMapping("/test/hystrix-fegin")
    public String test2() {
//        hystrixService.test();
        // 清空缓存
//        hystrixService.test2();
//        hystrixService.test();
//        hystrixService.test4();
        hystrixService.test5();
        return "ok";
    }

    @ResponseBody
    @GetMapping("/test/asyn")
    public String asyn() {
//        hystrixService.test();
        // 清空缓存
//        hystrixService.test2();
//        hystrixService.test();
//        hystrixService.test4();
        hystrixService.cacheKey();
        return "ok";
    }

    @ResponseBody
    @GetMapping("/test/cache")
    public String cache() {
        String s = test.testCachingTest();
        return s;
    }

}
