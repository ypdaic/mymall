package com.ypdaic.mymall.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import static org.springframework.cache.config.CacheManagementConfigUtils.CACHE_ADVISOR_BEAN_NAME;

/**
 * 必须继承
 */
@Component
public class BeanFactoryCacheOperationSourceAdvisorBeanPostProcess implements BeanPostProcessor, Ordered {

    @Autowired
    CacheInterceptor myCacheInterceptor;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (CACHE_ADVISOR_BEAN_NAME.equals(beanName)) {
            BeanFactoryCacheOperationSourceAdvisor beanFactoryCacheOperationSourceAdvisor = (BeanFactoryCacheOperationSourceAdvisor) bean;
            beanFactoryCacheOperationSourceAdvisor.setAdvice(myCacheInterceptor);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
