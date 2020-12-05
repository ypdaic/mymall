package com.ypdaic.mymall.product.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import static org.springframework.scheduling.config.TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME;

@Component
public class MyAsyncAnnotationBeanPostProcessorConfig implements BeanDefinitionRegistryPostProcessor, Ordered {

    private BeanFactory beanFactory;

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME.equals(beanName)) {
//            ConfigurableListableBeanFactory configurableListableBeanFactory = (ConfigurableListableBeanFactory) beanFactory;
//            configurableListableBeanFactory.registerSingleton(beanName, new MyAsyncAnnotationBeanPostProcessor());
//            return bean;
//        }
//        return null;
//    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory = beanFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinition beanDefinition = registry.getBeanDefinition(ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME);
        beanDefinition.setFactoryBeanName(MyAsyncConfig.class.getName());
        System.out.println("xxxx");
    }
}
