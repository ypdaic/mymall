package com.ypdaic.mymall.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static com.ypdaic.mymall.common.cache.LocalFirstLevelCacheInterceptor.FIRST_LEVEL_CACHE;

/**
 * @ClassName DelayDoubleDeletionInterceptor
 * @Description 延时双删处理
 * @Author daiyanping
 * @Date 2020/6/30
 * @Version 0.1`
 */
@Slf4j
@Service
public class DelayDoubleDeletionInterceptor extends AbstractCacheInterceptor implements ApplicationContextAware, SmartLifecycle {

    public static final String BEAN_NAME = "delayDoubleDeletionInterceptor";

    private volatile boolean running = false;

    //    TODO 根据业务可能需要调整
    // 延时时间
    private static long DELETE_TIME = 5;

    @Autowired
    RedisTemplate redisTemplate;

    ApplicationContext applicationContext;

    @Autowired
    RedissonClient redissonClient;

    RBlockingQueue<String> blockingQueue;

    RDelayedQueue<String> delayedQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 缓存名称
     */
    private String cacheName;

    @Override
    protected boolean matchMethod(Method method) {
        String name = method.getName();
        return "evict".equals(name);
    }

    @Override
    protected Object invokeMethod(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        Object argument = arguments[0];
        putQueue(argument.toString());
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void start() {
        RBlockingQueue<String> blockingQueue = redissonClient.getBlockingQueue("delay_double_delete_queue");
        this.blockingQueue = blockingQueue;
        this.delayedQueue = redissonClient.getDelayedQueue(blockingQueue);

        new Thread(() -> {
            Thread.currentThread().setName("DELAY_DOUBLE_DELETE_TASK");
            running = true;
            while (running) {
                String deleteKeyInfo = null;
                try {
                    if (redissonClient.isShutdown()) {
                        break;
                    }
                    deleteKeyInfo = blockingQueue.take();
                    String cacheName = deleteKeyInfo.substring(0, deleteKeyInfo.indexOf("::"));
                    String exchange = FIRST_LEVEL_CACHE + ".EXCHANGE" + "." + cacheName;
                    redisTemplate.delete(deleteKeyInfo);
                    rabbitTemplate.convertAndSend(exchange, "", deleteKeyInfo);
                } catch (Exception e) {
                    log.error("删除缓存异常!", e);
                    putQueue(deleteKeyInfo);
                }
            }
            if (!redissonClient.isShutdown()) {

                //销毁
                delayedQueue.destroy();
            }
        }).start();
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private void putQueue(String redisKey) {
        if (StringUtils.isNotEmpty(redisKey)) {
            delayedQueue.offer(redisKey, DELETE_TIME, TimeUnit.SECONDS);
        }
    }

}
