package com.ypdaic.mymall.product.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigrationTest {

    public static class Test {

    }

    @ConditionalOnMissingBean
    @Bean
    public Test getTest() {
        System.out.println("主配置类加载");
        return new Test();
    }

    /**
     * 内部配置类要比外部配置类先加载
     */
    @Configuration
    public static class ConfigrationTest2 {

        @ConditionalOnMissingBean
        @Bean
        public Test getTest() {
            System.out.println("内部配置类加载");
            return new Test();
        }
    }
}
