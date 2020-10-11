package com.ypdaic.mymall.product.thread;


import com.ypdaic.mymall.product.request.Request;
import com.ypdaic.mymall.product.request.RequestQueue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求处理线程池：单例
 * @author Administrator
 *
 */
@Component
public class RequestProcessorThreadPool {
	
	// 在实际项目中，你设置线程池大小是多少，每个线程监控的那个内存队列的大小是多少
	// 都可以做到一个外部的配置文件中
	// 我们这了就给简化了，直接写死了，好吧
	
	/**
	 * 线程池
	 */
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	@PostConstruct
	public void init() {
		RequestQueue requestQueue = RequestQueue.getInstance();
		
		for(int i = 0; i < 10; i++) {
			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
			requestQueue.addQueue(queue);  
			threadPool.submit(new RequestProcessorThread(queue));  
		}
	}


	
}
