package com.ypdaic.mymall.order.config;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.order.entity.Order;
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

    @Bean
    public Queue orderDelayQueue() {
        /**
         * 设置队列的过期时间
         */
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "order.event.exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        arguments.put("x-message-ttl", 60000);
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);

        return queue;
    }

    @Bean
    public Queue orderReleaseQueue() {
        Queue queue = new Queue("order.release.queue", true, false, false);
        return queue;
    }

    @Bean
    public Exchange orderEventExchange() {
        TopicExchange topicExchange = new TopicExchange("order.event.exchange", true, false);
        return topicExchange;
    }

    @Bean
    public Binding orderCreateOrderBinding(Queue orderDelayQueue, Exchange orderEventExchange) {
        return BindingBuilder.bind(orderDelayQueue).to(orderEventExchange).with("order.create.order").noargs();

    }

    @Bean
    public Binding orderReleaseOrderBinding(Queue orderReleaseQueue, Exchange orderEventExchange) {
        return BindingBuilder.bind(orderReleaseQueue).to(orderEventExchange).with("order.release.order").noargs();

    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "order.release.queue")
    public void listener(Order order, Channel channel, Message message) {
        log.info("收到订单消息: {}", order);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("手动确认失败");
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                log.error("消息重投递失败");
            }
        }
    }

}
