package com.ypdaic.mymall.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan(value = {"com.ypdaic.mymall.message.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication
@EnableScheduling
/**
 * 注入spring.factories 文件中
 * org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker对应的配置类
 * hystrix对应就注入了HystrixCommandAspect这个aop
 */
@EnableCircuitBreaker
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
