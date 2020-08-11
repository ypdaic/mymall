package com.ypdaic.mymall.order;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(value = {"com.ypdaic.mymall.order.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication(exclude = {TaskSchedulingAutoConfiguration.class, org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration.class})
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.member",
        "com.ypdaic.mymall.fegin.product", "com.ypdaic.mymall.fegin.cart", "com.ypdaic.mymall.fegin.ware", "com.ypdaic.mymall.fegin.message"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
