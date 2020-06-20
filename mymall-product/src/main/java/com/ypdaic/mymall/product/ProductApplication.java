package com.ypdaic.mymall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan(value = {"com.ypdaic.mymall.product.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication(exclude = {TaskSchedulingAutoConfiguration.class})
//@SpringBootApplication
@EnableScheduling
@EnableRedisHttpSession
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.coupon", "com.ypdaic.mymall.fegin.ware", "com.ypdaic.mymall.fegin.search"})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
