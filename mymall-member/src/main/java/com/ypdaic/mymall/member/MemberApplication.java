package com.ypdaic.mymall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(value = {"com.ypdaic.mymall.member.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ypdaic.mymall.fegin.coupon")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

}
