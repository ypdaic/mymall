package com.ypdaic.mymall.common.cache;

import com.ypdaic.mymall.common.annotation.MyCacheable;
import lombok.Data;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.TaskManagementConfigUtils;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存本地锁支持
 * @author daiyanping
 */
@Data
@Component
@Scope("prototype")
public class LocalLockCacheInterceptor extends AbstractCacheLockInterceptor{

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private static String lockSuffix = "~lock";

    private final Map<String, Object> lockMap = new ConcurrentHashMap(16);

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        /**
         * 清除本地锁,防止潜在内存泄漏风险,锁不多的情况下暂不使用,后续考虑使用
         */
        threadPoolTaskScheduler.schedule(() -> {
            lockMap.clear();
        }, new CronTrigger("0 0 4 * * ?"));
    }

    /**
     * 重新实现同步的get方法
     * @param key
     * @param valueLoader
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader, MethodInvocation invocation) throws Throwable {
        Method targetMethod = CacheConfig.getMethod();
        MyCacheable myCache = targetMethod.getAnnotation(MyCacheable.class);
        if (Objects.nonNull(myCache)) {
            // 不使用一级缓存就走本地锁
            if (!myCache.useFirstCache()) {
                return getCacheValue(key, invocation);
            }
        }
        return getCacheValue(key, invocation);
    }

    private <T> T getCacheValue(Object key, MethodInvocation invocation) throws Throwable {
        Cache cache = (Cache) invocation.getThis();
        // 获取到结果立马返回
        Cache.ValueWrapper result = cache.get(key);
        if (result != null) {
            syncCacheTimeForTwoCache(cache.getName(), (String) key);
            return (T) result.get();
        }

        String lock = key + lockSuffix;

        Object o = lockMap.computeIfAbsent(lock, (k) -> {
            return new Object();
        });

//         先使用本地锁
        synchronized (key) {
//            本地锁再次尝试获取结果，有就立马返回
            result = cache.get(key);
            if (result != null) {
                syncCacheTimeForTwoCache(cache.getName(), (String) key);
                return (T) result.get();
            }
            return (T) invocation.proceed();
        }
    }
}
