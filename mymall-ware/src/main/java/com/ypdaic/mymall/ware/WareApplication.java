package com.ypdaic.mymall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(value = {"com.ypdaic.mymall.ware.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.product"})
public class WareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareApplication.class, args);
    }

}
