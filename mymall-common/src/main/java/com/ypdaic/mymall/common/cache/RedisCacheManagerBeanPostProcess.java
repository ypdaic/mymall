package com.ypdaic.mymall.common.cache;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisCacheManagerBeanPostProcess
 * @Description TODO
 * @Author daiyanping
 * @Date 2020/6/16
 * @Version 0.1
 */
@Component
public class RedisCacheManagerBeanPostProcess implements BeanPostProcessor {

    @Autowired
    RedisCacheManagerInterceptor redisCacheManagerInterceptor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RedisCacheManager) {
            RedisCacheManager redisCacheManager = (RedisCacheManager) bean;
            ProxyFactory factory = new ProxyFactory();
            factory.setExposeProxy(true);
            redisCacheManagerInterceptor.setRedisCacheManager(redisCacheManager);
            factory.addAdvisor(new DefaultPointcutAdvisor(redisCacheManagerInterceptor));
            factory.setTarget(redisCacheManager);
            redisCacheManager = (RedisCacheManager) factory.getProxy(RedisCacheManager.class.getClassLoader());
            return redisCacheManager;
        }
        return bean;
    }
}
