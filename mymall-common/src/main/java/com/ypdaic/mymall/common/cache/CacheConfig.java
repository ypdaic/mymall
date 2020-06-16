package com.ypdaic.mymall.common.cache;

import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @ClassName CacheConfig
 * @Description TODO
 * @Author daiyanping
 * @Date 2020-06-04
 * @Version 0.1
 */
@Configuration
public class CacheConfig {

    private static final ThreadLocal<Method> cacheMethod = new ThreadLocal<>();

    @Bean
    public CacheInterceptor myCacheInterceptor(CacheOperationSource cacheOperationSource) {
        CacheInterceptor interceptor = new MyCacheInterceptor();
        interceptor.setCacheOperationSource(cacheOperationSource);
        return interceptor;
    }

    /**
     * 用于@MyCacheable 注解自定义属性获取
     */
    public static class MyCacheInterceptor extends CacheInterceptor {
        @Override
        protected Object execute(CacheOperationInvoker invoker, Object target, Method method, Object[] args) {
            cacheMethod.set(method);
            try {
                Object execute = super.execute(invoker, target, method, args);
                return execute;
            } finally {
                cacheMethod.remove();
            }

        }
    }

    public static Method getMethod() {
        return cacheMethod.get();
    }
}
