package com.ypdaic.mymall.common.cache;

import com.ypdaic.mymall.common.annotation.MyCacheable;
import com.ypdaic.mymall.common.config.CacheConfig;
import lombok.Data;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * redis cache 缓存时间 设置随机值支持
 * @author daiyanping
 */
@Component
@Scope("prototype")
@Data
public class RedisCacheTTLInterceptor extends AbstractCacheInterceptor {

    private volatile MyRedisCache myRedisCache;

    private final Object lockMonitor = new Object();

    private List<String> cacheNames = new ArrayList<String>(0);

    @Override
    protected boolean matchMethod(Method method) {
        return "put".equals(method.getName());
    }

    @Override
    protected Object invokeMethod(MethodInvocation invocation) throws Throwable {
        Method method = CacheConfig.getMethod();
        MyCacheable myCache = method.getAnnotation(MyCacheable.class);
        Object[] arguments = invocation.getArguments();
        Cache cache = (Cache) invocation.getThis();
        RedisCache redisCache = null;
        if (cache instanceof TransactionAwareCacheDecorator) {
            redisCache = (RedisCache) ((TransactionAwareCacheDecorator) cache).getTargetCache();
        } else {
            redisCache = (RedisCache) cache;
        }
        if (Objects.isNull(myRedisCache)) {
            synchronized (lockMonitor) {
                if (Objects.isNull(myRedisCache)) {
                    myRedisCache = new MyRedisCache(redisCache.getName(), redisCache.getNativeCache(), redisCache.getCacheConfiguration());
                }

            }

        }
        Object cacheValue = myRedisCache.preProcessCacheValue(arguments[1]);

        if (!redisCache.isAllowNullValues() && cacheValue == null) {

            throw new IllegalArgumentException(String.format(
                    "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                    cache.getName()));
        }
        Duration ttl = null;
        if (Objects.nonNull(myCache)) {
            boolean useCustomize = myCache.useCustomExpireDate();

            if (useCustomize) {
                long i = myCache.expireDate();
                ttl = Duration.ofSeconds(i);
            }

        }else {
            ttl = myRedisCache.getCacheConfiguration().getTtl();
            if (shouldAddRandom(cache.getName())) {
                Random random = new Random();
                int i = random.nextInt(30);
                ttl = ttl.plusMinutes(i);
            }

        }

        syncCacheTimeForTTL(ttl);
        redisCache.getNativeCache().put(cache.getName(), myRedisCache.createAndConvertCacheKey(arguments[0]), myRedisCache.serializeCacheValue(cacheValue), ttl);
        return null;
    }

    /**
     * 需要设置随机值的缓存
     * @param cacheName
     * @return
     */
    private boolean shouldAddRandom(String cacheName) {
        return this.cacheNames.contains(cacheName);
    }

    private static class MyRedisCache extends RedisCache {

        /**
         * Create new {@link RedisCache}.
         *
         * @param name        must not be {@literal null}.
         * @param cacheWriter must not be {@literal null}.
         * @param cacheConfig must not be {@literal null}.
         */
        protected MyRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
            super(name, cacheWriter, cacheConfig);
        }

        @Override
        protected Object preProcessCacheValue(Object value) {
            return super.preProcessCacheValue(value);
        }

        private byte[] createAndConvertCacheKey(Object key) {
            return super.serializeCacheKey(super.createCacheKey(key));
        }

        @Override
        protected byte[] serializeCacheValue(Object value) {
            return super.serializeCacheValue(value);
        }
    }

}
