package com.ypdaic.mymall.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan(value = {"com.ypdaic.mymall.seckill", "com.ypdaic.mymall.common.**"})
@SpringBootApplication(exclude = {TaskSchedulingAutoConfiguration.class, DataSourceAutoConfiguration.class})
//@SpringBootApplication
@EnableScheduling
@EnableRedisHttpSession
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.coupon", "com.ypdaic.mymall.fegin.ware", "com.ypdaic.mymall.fegin.search", "com.ypdaic.mymall.fegin.product"})
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
