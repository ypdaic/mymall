package com.ypdaic.mymall.fegin.config;

import feign.RequestInterceptor;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new RequestHeaderInterceptor();
    }

    @Bean
    public SetterFactory setterFactory() {
        return new MySetterFactory();
    }

}
