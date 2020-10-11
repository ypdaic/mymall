package com.ypdaic.mymall.product.service;


import com.ypdaic.mymall.product.request.Request;

/**
 * 请求异步执行的service
 * @author Administrator
 *
 */
public interface RequestAsyncProcessService {

	void process(Request request);
	
}
