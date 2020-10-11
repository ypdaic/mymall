package com.ypdaic.mymall.product.dao.impl;

import com.ypdaic.mymall.product.dao.RedisDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {

	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public void set(String key, String value) {
		redisTemplate.boundValueOps(key).set(value);
	}

	@Override
	public String get(String key) {
		return (String) redisTemplate.boundValueOps(key).get();
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

}
