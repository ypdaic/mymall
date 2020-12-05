package com.ypdaic.mymall.product.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCacheAspect;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.ypdaic.mymall.fegin.ware.IWareFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName HystrixServiceImpl
 * @Description TODO
 * @Author daiyanping
 * @Date 2020/7/8
 * @Version 0.1
 */
@Service
// 只使用CacheRemove注解的话，需要开启这个aop
@Import(HystrixCacheAspect.class)
public class HystrixServiceImpl {

    @Autowired
    IWareFeignService wareFeignService;

    @Autowired
    HystrixServiceImpl hystrixService;

    @HystrixCommand(
            fallbackMethod = "queryContentsFallback",
            // 用于区分不同的commandProperties配置，相同的commandKey，使用相同的commandProperties配置
            // command key ，代表了一类 command，一般来说，代表了下游依赖服务的某个接口。
            commandKey = "queryContents",
            //服务分组
            // 代表了某一个下游依赖服务，这是很合理的，一个依赖服务可能会暴露出来多个接口，每个接口就是一个 command key。command group 在逻辑上对一堆 command key 的调用次数、成功次数、timeout 次数、失败次数等进行统计，可以看到某一个服务整体的一些访问情况。一般来说，推荐根据一个服务区划分出一个线程池，command key 默认都是属于同一个线程池的。
            groupKey = "querygroup-one",
            commandProperties = {
                    // 信号量隔离模式，信号量大小，默认10
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "10"),
                    // 隔离模式
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 线程池隔离模式下，线程执行的超时时间，默认1000
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    // 是否开启超时处理，默认true
                    @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
                    // 是否开启超时后，线程中断，默认true,THREAD模式下有效
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                    // 是否开启取消线程后，线程中断，默认true,THREAD模式下有效,不支持配置
//                    @HystrixProperty(name = "execution.isolation.thread.interruptOnFutureCancel", value = "true"),


                    // 是否启用熔断功能，默认true
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    // 判断熔断开启的请求最低阈值，默认20，在metrics.rollingStats.timeInMilliseconds时间窗口的请求数才有效
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    // 熔断开启后，等待一段时间，容许通过一个请求，如果可以正常请求就关闭熔断，如果不能正常请求，继续等待sleepWindowInMilliseconds时间后，尝试容许请求，默认5s
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    // 判断熔断开启的错误请求数百分比，从统计的健康值中获取，默认50%
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5000"),

                    // 调用fallback的最大请求数，fallback使用的信号量机制，默认10，如果fallback中没有调用其他服务，只是返回简单错误，可以设置一个比较大的值
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 是否开启fallback，默认开启
                    @HystrixProperty(name = "fallback.enabled", value = "true"),


                    // 是否开启请求缓存，默认true
                    @HystrixProperty(name = "requestCache.enabled", value = "true"),

                    // 是否开启请求打印日志，默认true
                    @HystrixProperty(name = "requestLog.enabled", value = "true"),

                    // 两次健康状态计算的等待时间，健康状态的计算会消耗cpu，默认500ms
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500"),
                    // 指定线程池的key
                    @HystrixProperty(name ="threadPoolKeyOverride", value = "test")
            },
            //线程分组，区分不同的线程池，同一个threadPoolKey使用同一线程池，如果不指定则使用groupKey,也可以指定
            threadPoolKey = "queryContentshystrixJackpool",
            threadPoolProperties = {
                    // 线程池线程数
                    @HystrixProperty(name = "coreSize", value = "10"),
                    // 线程池线程数最大值,allowMaximumSizeToDivergeFromCoreSize为true有效，该属性不可配置
//            @HystrixProperty(name = "maximumSize", value = "10"),
                    // 允许maximumSize偏离coreSize，该属性不可配置，默认false
//            @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "false"),
                    // 线程空闲等待时间
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),
                    // 队列最大任务数，默认-1，一旦指定不可修改，1是创建线程池指定队列的大小，2是线程池会被缓存，使用threadPoolKey为关键字进行缓存
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    // 队列拒绝阈值，支持动态指定，也就是认为判断队列数量，超过就拒绝
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "2")
            })
    // 指定缓存的key，缓存在同一个请求上下文中有效
    @CacheResult(cacheKeyMethod = "cacheKey")
    public String test() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @HystrixCommand(
            fallbackMethod = "queryContentsFallback",
            // 用于区分不同的commandProperties配置，相同的commandKey，使用相同的commandProperties配置
            commandKey = "queryContents",
            //服务分组
            groupKey = "querygroup-one",
            commandProperties = {
                    // 信号量隔离模式，信号量大小，默认10
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "10"),
                    // 隔离模式
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 线程池隔离模式下，线程执行的超时时间，默认1000
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    // 是否开启超时处理，默认true
                    @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
                    // 是否开启超时后，线程中断，默认true,THREAD模式下有效
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                    // 是否开启取消线程后，线程中断，默认true,THREAD模式下有效,不支持配置
//                    @HystrixProperty(name = "execution.isolation.thread.interruptOnFutureCancel", value = "true"),


                    // 是否启用熔断功能，默认true
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    // 判断熔断开启的请求最低阈值，默认20，在metrics.rollingStats.timeInMilliseconds时间窗口的请求数才有效
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    // 熔断开启后，等待一段时间，容许通过一个请求，如果可以正常请求就关闭熔断，如果不能正常请求，继续等待sleepWindowInMilliseconds时间后，尝试容许请求，默认5s
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    // 判断熔断开启的错误请求数百分比，从统计的健康值中获取，默认50%
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "5000"),

                    // 调用fallback的最大请求数，fallback使用的信号量机制，默认10，如果fallback中没有调用其他服务，只是返回简单错误，可以设置一个比较大的值
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 是否开启fallback，默认开启
                    @HystrixProperty(name = "fallback.enabled", value = "true"),


                    // 是否开启请求缓存，默认true
                    @HystrixProperty(name = "requestCache.enabled", value = "true"),

                    // 是否开启请求打印日志，默认true
                    @HystrixProperty(name = "requestLog.enabled", value = "true"),

                    // 两次健康状态计算的等待时间，健康状态的计算会消耗cpu，默认500ms
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500")
            },
            //线程分组，区分不同的线程池，同一个threadPoolKey使用同一线程池，如果不指定则使用groupKey
            threadPoolKey = "queryContentshystrixJackpool",
            threadPoolProperties = {
                    // 线程池线程数
                    @HystrixProperty(name = "coreSize", value = "10"),
                    // 线程池线程数最大值,allowMaximumSizeToDivergeFromCoreSize为true有效，该属性不可配置
//            @HystrixProperty(name = "maximumSize", value = "10"),
                    // 允许maximumSize偏离coreSize，该属性不可配置，默认false
//            @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "false"),
                    // 线程空闲等待时间
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),
                    // 队列最大任务数，默认-1，一旦指定不可修改，1是创建线程池指定队列的大小，2是线程池会被缓存，使用threadPoolKey为关键字进行缓存
                    @HystrixProperty(name = "maxQueueSize", value = "2"),
                    // 队列拒绝阈值，支持动态指定，也就是认为判断队列数量，超过就拒绝
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "10")
            })
    // 清除缓存，执行时机在调用业务方法后执行也就是AbstractHystrixCommand的flushCache方法
    @CacheRemove(cacheKeyMethod = "cacheKey", commandKey = "queryContents")
    public String test2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @CacheRemove(cacheKeyMethod = "cacheKey", commandKey = "queryContents")
    public void test4() {

    }

    public String queryContentsFallback() {
        return "error";
    }

    @Async
    public String cacheKey() {
        return "test";
    }

    public void test5() {
        wareFeignService.getFare(1L);
    }

//    @Scheduled(cron = "*/5 * * * * ?")
    @Transactional
    public void test23() {
        System.out.println("xxxxx");
    }
}
