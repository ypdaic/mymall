package com.ypdaic.mymall.fegin.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Feign;
import feign.Target;
import feign.hystrix.SetterFactory;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 使用自定义的commandKey
 */
public class MySetterFactory implements SetterFactory {
    @Override
    public HystrixCommand.Setter create(Target<?> target, Method method) {
        String groupKey = target.name();
        com.ypdaic.mymall.fegin.config.HystrixCommand hystrixCommand = method.getAnnotation(com.ypdaic.mymall.fegin.config.HystrixCommand.class);
        String commandKey = null;
        if (Objects.nonNull(hystrixCommand)) {
            commandKey = hystrixCommand.commandKey();
//            HystrixCommandProperties.Setter().withExecutionIsolationStrategy()
        } else {
            commandKey = Feign.configKey(target.type(), method);
        }


        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
    }
}
