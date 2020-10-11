package com.ypdaic.mymall.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @ClassName RedissonConfig
 * @Description TODO
 * @Author daiyanping
 * @Date 2019-05-30
 * @Version 0.1
 */
@Configuration
@EnableCaching
public class RedissonConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public RedissonClient redisson() throws IOException {
//        Config config = Config.fromYAML(new ClassPathResource("redisson_dev_config.yml").getInputStream());
//        Config config = Config.fromYAML(new ClassPathResource("redisson-"+ profile +"-config.yml").getInputStream());
//        Config config = Config.fromYAML(new ClassPathResource("redisson-"+ profile +"-sentinel-config.yml").getInputStream());
        Config config = Config.fromYAML(new ClassPathResource("redisson-"+ profile +"-cluster-config.yml").getInputStream());
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
