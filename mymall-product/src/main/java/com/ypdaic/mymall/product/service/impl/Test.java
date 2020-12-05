package com.ypdaic.mymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Test {

//    @Autowired
//    Test test;

    @Async
    public void testAsync() {

    }

    @Transactional
    public void testTransaction() {

    }

    @Cacheable(cacheNames = "APP_CACHE", key = "'test'")
    public String testCachingTest() {
        System.out.println("xxxxxx");
        return "test";
    }
}
