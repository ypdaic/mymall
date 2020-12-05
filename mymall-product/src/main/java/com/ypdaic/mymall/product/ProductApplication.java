package com.ypdaic.mymall.product;

import com.ypdaic.mymall.common.cache.BeanFactoryCacheOperationSourceAdvisorBeanPostProcess;
import com.ypdaic.mymall.product.config.MyAsyncConfigurationSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(value = {"com.ypdaic.mymall.product.**", "com.ypdaic.mymall.common.**"})
@SpringBootApplication(exclude = {TaskSchedulingAutoConfiguration.class})
//@SpringBootApplication
@EnableScheduling
@EnableRedisHttpSession
@EnableFeignClients(basePackages = {"com.ypdaic.mymall.fegin.coupon", "com.ypdaic.mymall.fegin.ware", "com.ypdaic.mymall.fegin.search"})
/**
 * 注入spring.factories 文件中
 * org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker对应的配置类
 * hystrix对应就注入了HystrixCommandAspect这个aop
 */
@EnableAsync()
@EnableTransactionManagement()
@EnableCircuitBreaker
@Import({MyAsyncConfigurationSelector.class})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
