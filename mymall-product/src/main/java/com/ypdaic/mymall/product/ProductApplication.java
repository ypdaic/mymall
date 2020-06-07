package com.ypdaic.mymall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(value = {"com.ypdaic.mymall.product.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication
@EnableScheduling
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
