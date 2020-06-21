package com.ypdaic.mymall.mymallsearch;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class, RedissonAutoConfiguration.class, MybatisPlusAutoConfiguration.class, DruidDataSourceAutoConfigure.class, CacheAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.product"})
public class MymallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallSearchApplication.class, args);
    }

}
