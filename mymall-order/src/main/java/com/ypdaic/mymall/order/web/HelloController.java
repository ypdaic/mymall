package com.ypdaic.mymall.order.web;

import cn.hutool.core.lang.UUID;
import com.ypdaic.mymall.order.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class HelloController {

    @Autowired
    AmqpTemplate amqpTemplate;

//    @Autowired
//    RocketMQTemplate rocketMQTemplate;

    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        return page;
    }

    @ResponseBody
    @GetMapping("/test/createOrderTest")
    public String createOrderTest() {
        Order order = new Order();
        order.setOrderSn(UUID.fastUUID().toString());
        amqpTemplate.convertAndSend("order.event.exchange", "order.create.order", order);
        return "ok";
    }

//    @ResponseBody
//    @GetMapping("/test/rocketmq")
//    public String rocketmq() {
//        Order order = new Order();
//        rocketMQTemplate.sendAndReceive("test", order, String.class);
//        return "ok";
//    }

}
