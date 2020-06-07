package com.ypdaic.mymall.common.annotation;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD, ElementType.TYPE
})
@Inherited
@Documented
@Cacheable
public @interface MyCaching {

    MyCacheable[] cacheable() default {};

    CachePut[] put() default {};

    CacheEvict[] evict() default {};
}
