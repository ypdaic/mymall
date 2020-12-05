package com.ypdaic.mymall.product.config;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;

import java.lang.reflect.Field;

public class MyAsyncAnnotationBeanPostProcessor extends AsyncAnnotationBeanPostProcessor {

    private BeanFactory myBeanFactory;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (this.advisor == null || bean instanceof AopInfrastructureBean) {
            // Ignore AOP infrastructure such as scoped proxies.
            return bean;
        }

        if (bean instanceof Advised) {
            Advised advised = (Advised) bean;
            if (!advised.isFrozen() && isEligible(AopUtils.getTargetClass(bean))) {
                // Add our local Advisor to the existing proxy's Advisor chain...
                if (this.beforeExistingAdvisors) {
                    advised.addAdvisor(0, this.advisor);
                }
                else {
                    advised.addAdvisor(this.advisor);
                }
                return bean;
            }
        }

        if (isEligible(bean, beanName)) {
            if (myBeanFactory == null) {
                try {
                    Class clazz = this.getClass();
                    for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                        Field[] declaredFields = clazz.getDeclaredFields();
                        for (Field declaredField : declaredFields) {
                            String name = declaredField.getName();
                            if ("beanFactory".equals(name)) {
                                Field field = clazz.getDeclaredField("beanFactory" );
                                field.setAccessible(true);
                                myBeanFactory = (BeanFactory) field.get(this);
                                break;
                            }
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException("xxxxx", e);
                }
            }

            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) myBeanFactory;
            Object singleton = defaultListableBeanFactory.getSingleton(beanName);
            if (singleton instanceof Advised) {
                Advised advised = (Advised) singleton;
                if (!advised.isFrozen()) {

                    // Add our local Advisor to the existing proxy's Advisor chain...
                    if (this.beforeExistingAdvisors) {
                        advised.addAdvisor(0, this.advisor);
                    }
                    else {
                        advised.addAdvisor(this.advisor);
                    }
                    return bean;

                }
            } else {

                ProxyFactory proxyFactory = prepareProxyFactory(bean, beanName);
                if (!proxyFactory.isProxyTargetClass()) {
                    evaluateProxyInterfaces(bean.getClass(), proxyFactory);
                }
                proxyFactory.addAdvisor(this.advisor);
                customizeProxyFactory(proxyFactory);
                return proxyFactory.getProxy(getProxyClassLoader());
            }

        }

        // No proxy needed.
        return bean;
    }
}
