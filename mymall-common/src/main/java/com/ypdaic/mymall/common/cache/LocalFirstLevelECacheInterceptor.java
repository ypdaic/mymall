package com.ypdaic.mymall.common.cache;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.annotation.MyCacheable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

@Data
@Slf4j
@Scope("prototype")
public class LocalFirstLevelECacheInterceptor extends AbstractFirstLevelCacheInterceptor implements InitializingBean, ChannelAwareMessageListener, BeanFactoryAware {

    protected static final String beanName = "LocalFirstLevelECacheInterceptor";

    public static final String FIRST_LEVEL_CACHE = "FIRST_LEVEL_CACHE";

    private static final ThreadLocal<org.springframework.cache.Cache> cacheLocal = new ThreadLocal<>();

    /**
     * 一级缓存
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * 缓存名称
     */
    private String cacheName;

    /**
     * bean 工厂
     */
    private BeanFactory beanFactory;

    /**
     * 对应的交换器
     */
    private String exchange;

    /**
     * 对应队列
     */
    private String queueName;

    /**
     * 路由key
     */
    private String routingKey;

    /**
     * 清空缓存对应的key
     */
    private String clearKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("applicationTaskExecutor")
    ThreadPoolTaskExecutor executor;

    @Override
    protected boolean matchMethod(Method method) {
        String name = method.getName();
        return "get".equals(name) || "evict".equals(name) || "clear".equals(name);
    }

    @Override
    protected Object invokeMethod(MethodInvocation invocation) throws Throwable {
        Method targetMethod = CacheConfig.getMethod();
        MyCacheable myCache = targetMethod.getAnnotation(MyCacheable.class);
        if (Objects.nonNull(myCache)) {
            // 走一级缓存
            if (myCache.useFirstCache() && myCache.firstCacheType().equals(FirstCacheTypeEnum.ECACHE)) {
                return getCacheValue(invocation);
            }
        }

        return invocation.proceed();
    }

    private Object getCacheValue(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        String name = method.getName();
        if (!"clear".equals(name)) {
            Object[] objects = invocation.getArguments();
            String key = (String) objects[0];
            switch (name) {
                case "get":
                    org.springframework.cache.Cache targetCache = (org.springframework.cache.Cache) invocation.getThis();
                    RedisCache redisCache = null;
                    if (targetCache instanceof TransactionAwareCacheDecorator) {
                        redisCache = (RedisCache) ((TransactionAwareCacheDecorator) targetCache).getTargetCache();
                    } else {
                        redisCache = (RedisCache) targetCache;
                    }
                    cacheLocal.set(redisCache);
                    Cache cache = cacheManager.getCache(cacheName);
                    Element element = cache.get(key);
                    if (Objects.isNull(element)) {
                        try {
                            Object result = invocation.proceed();
                            element = new Element(key, result);
                            cache.putIfAbsent(element);
                        } catch (Throwable throwable) {
                            log.error("缓存获取异常", throwable);
                            throw new RuntimeException("缓存获取异常");
                        }
                    }
                    return element.getObjectValue();
                case "evict":
                    rabbitTemplate.convertAndSend(exchange, "", key);
                    return null;
                default :
                    return null;

            }
        } else {
            rabbitTemplate.convertAndSend(exchange, "", clearKey);
        }

        return null;
    }

    /**
     * DefaultSingletonBeanRegistry的getSingleton方法 会持有singletonObjects对象的锁，
     * main 线程 在 getBean
     *
     * 和  AbstractMessageListenerContainer 中的 SimpleAsyncTaskExecutor 线程池
     */
    @Override
    public void afterPropertiesSet() {
        String random = UUID.randomUUID().toString();
        int i = ++increment;
        String queueName = FIRST_LEVEL_CACHE + "." + this.cacheName + "." + random;
        String exchange = FIRST_LEVEL_CACHE + ".EXCHANGE" + "." + cacheName;
        String bindingName = exchange + "." + cacheName + i;

        this.exchange = exchange;
        this.queueName = queueName;
        this.clearKey = queueName + "." + "clear" + "." + System.currentTimeMillis();

        org.springframework.amqp.core.Queue queue = new org.springframework.amqp.core.Queue(queueName, false, false, true);
        FanoutExchange fanoutExchange = new FanoutExchange(exchange, false, true);

        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);

        ((ConfigurableBeanFactory) this.beanFactory)
                .registerSingleton(queueName, queue);

        if (!beanFactory.containsBean(exchange)) {
            ((ConfigurableBeanFactory) this.beanFactory)
                    .registerSingleton(exchange, fanoutExchange);
        }


        ((ConfigurableBeanFactory) this.beanFactory)
                .registerSingleton(bindingName, binding);

        RabbitListenerContainerFactory rabbitListenerContainerFactory = (RabbitListenerContainerFactory) beanFactory.getBean("rabbitListenerContainerFactory");
        AmqpAdmin amqpAdmin = (AmqpAdmin) beanFactory.getBean("amqpAdmin");

        AbstractMessageListenerContainer listenerContainer = (AbstractMessageListenerContainer) rabbitListenerContainerFactory.createListenerContainer();
        listenerContainer.setupMessageListener(this);

        // 队列名称要和容器中的队列名称一样，否则会有问题
        listenerContainer.setQueueNames(queueName);
        listenerContainer.setAmqpAdmin(amqpAdmin);

        executor.execute(() -> {
            listenerContainer.start();
        });

    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        String s = new String(body);
        if (s.equals(clearKey)) {
            cacheManager.getCache(cacheName).removeAll();
        } else {
            cacheManager.getCache(cacheName).remove(s);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 注入一级缓存的BeanDefinition
     * @param beanFactory
     * @param cacheName
     */
    public static String registerBeanDefinition(BeanFactory beanFactory, String cacheName) {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        AnnotatedGenericBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(LocalFirstLevelECacheInterceptor.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("cacheName", cacheName);
        annotatedBeanDefinition.setPropertyValues(propertyValues);
        String name = beanName + ++beanNameIncrement;
        defaultListableBeanFactory.registerBeanDefinition(name, annotatedBeanDefinition);
        return name;

    }
}
