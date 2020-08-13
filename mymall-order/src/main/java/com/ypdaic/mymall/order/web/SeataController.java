package com.ypdaic.mymall.order.web;

import com.ypdaic.mymall.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName SeataController
 * @Description TODO
 * @Author daiyanping
 * @Date 2020/7/13
 * @Version 0.1
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class SeataController {

    @Autowired
    IOrderService orderService;

    @GetMapping("/seata")
    public String test() {
        orderService.seataTest();
        return "ok";
    }

    @GetMapping("/seata-sharding")
    public String testSharding() {
        orderService.seataShardingTest();
        return "ok";
    }

    @ResponseBody
    @GetMapping("/undertow")
    public String undertow() {
//        orderService.seataTest();
        return "ok";
    }
}
