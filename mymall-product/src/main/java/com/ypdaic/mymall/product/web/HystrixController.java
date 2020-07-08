package com.ypdaic.mymall.product.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.ypdaic.mymall.product.service.impl.HystrixServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
