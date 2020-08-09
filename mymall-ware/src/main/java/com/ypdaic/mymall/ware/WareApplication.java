package com.ypdaic.mymall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(value = {"com.ypdaic.mymall.ware.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.product", "com.ypdaic.mymall.fegin.member", "com.ypdaic.mymall.fegin.order", "com.ypdaic.mymall.fegin.message"})
public class WareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }

}
