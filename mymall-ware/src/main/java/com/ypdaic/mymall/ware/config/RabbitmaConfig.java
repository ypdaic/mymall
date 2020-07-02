package com.ypdaic.mymall.ware.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Configuration
public class RabbitmaConfig {

    /**
     * 库存延时队列，库存锁定一段时间后，进行解除库存
     * @return
     */
    @Bean
    public Queue stockDelayQueue() {
        /**
         * 设置队列的过期时间
         */
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "stock.event.exchange");
        arguments.put("x-dead-letter-routing-key", "stock.release");
        arguments.put("x-message-ttl", 120000);
        Queue queue = new Queue("stock.delay.queue", true, false, false, arguments);

        return queue;
    }

    /**
     * 解锁库存队列，消费这个队列，检查订单状态，确实是否需要解锁库存
     * @return
     */
    @Bean
    public Queue stockReleaseQueue() {
        Queue queue = new Queue("stock.release.stock.queue", true, false, false);
        return queue;
    }

    /**
     * 库存交换器
     * @return
     */
    @Bean
    public Exchange stockEventExchange() {
        TopicExchange topicExchange = new TopicExchange("stock.event.exchange", true, false);
        return topicExchange;
    }

    @Bean
    public Binding stockLockedBinding(Queue stockDelayQueue, Exchange stockEventExchange) {
        return BindingBuilder.bind(stockDelayQueue).to(stockEventExchange).with("stock.locked").noargs();

    }

    @Bean
    public Binding stockReleaseStockBinding(Queue stockReleaseQueue, Exchange stockEventExchange) {
        return BindingBuilder.bind(stockReleaseQueue).to(stockEventExchange).with("stock.release").noargs();

    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @RabbitListener(queues = "order.release.queue")
//    public void listener(Order order, Channel channel, Message message) {
//        log.info("收到订单消息: {}", order);
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (IOException e) {
//            log.error("手动确认失败");
//            try {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            } catch (IOException ex) {
//                log.error("消息重投递失败");
//            }
//        }
//    }

}
