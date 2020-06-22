package com.ypdaic.mymall.fegin.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new RequestHeaderInterceptor();
    }

}
