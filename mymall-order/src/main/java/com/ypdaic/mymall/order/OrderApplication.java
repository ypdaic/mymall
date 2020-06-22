package com.ypdaic.mymall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(value = {"com.ypdaic.mymall.order.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication(exclude = {TaskSchedulingAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.member", "com.ypdaic.mymall.fegin.product", "com.ypdaic.mymall.fegin.cart", "com.ypdaic.mymall.fegin.ware"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
