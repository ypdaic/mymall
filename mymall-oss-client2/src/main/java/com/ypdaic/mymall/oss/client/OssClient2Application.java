package com.ypdaic.mymall.oss.client;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {RabbitAutoConfiguration.class, RedissonAutoConfiguration.class, MybatisPlusAutoConfiguration.class, DruidDataSourceAutoConfigure.class, CacheAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class OssClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(OssClient2Application.class, args);
    }

}
