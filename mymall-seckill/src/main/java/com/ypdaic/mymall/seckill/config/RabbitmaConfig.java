package com.ypdaic.mymall.seckill.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Slf4j
@Configuration
public class RabbitmaConfig {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;

    /**
     * 同一队列中，有2个消息A和B，A延时3小时，先进。B延时2小时，后进。那么AB会在3小时后才会被消费
     * 所以一般设置队列的过期时间，所有的消息过期时间一致，就不会有上面的问题
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {
        /**
         * 设置队列的过期时间
         */
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "order.event.exchange");
        // 过期后发往order.release.order这个队列
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

    /**
     * 订单创建的消息发往延时队列
     * @param orderDelayQueue
     * @param orderEventExchange
     * @return
     */
    @Bean
    public Binding orderCreateOrderBinding(Queue orderDelayQueue, Exchange orderEventExchange) {
        return BindingBuilder.bind(orderDelayQueue).to(orderEventExchange).with("order.create.order").noargs();

    }

    @Bean
    public Binding orderReleaseOrderBinding(Queue orderReleaseQueue, Exchange orderEventExchange) {
        return BindingBuilder.bind(orderReleaseQueue).to(orderEventExchange).with("order.release.order").noargs();

    }

    /**
     * 订单释放直接和库存释放进行绑定
     * @param orderReleaseQueue
     * @param orderEventExchange
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBinding(Queue orderReleaseQueue, Exchange orderEventExchange) {
        return new Binding("stock.release.stock.queue",  Binding.DestinationType.QUEUE, "order.event.exchange", "order.release.other.#", null);

    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) throws Exception {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setMandatory(true);
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            // exchange收到消息
            // 将每一个发送的消息在数据库做好记录
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    // TODO 更新数据库消息发送状态
                }
            }
        });

        // 设置消息抵达队列的确认回调
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定的队列，就触发这个失败回调
             * @param message 投递失败的消息
             * @param replyCode  回复的状态吗
             * @param replyText 回复的文本 内容
             * @param exchange 当时这个消息发送给那个交换器
             * @param routingKey 当时这个消息用那个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.error("消息发送到队列失败，message:{}， replyCode:{}， replyText:{}, exchange:{}, routingKey:{}", message,
                        replyCode, replyText, exchange, routingKey);

            }
        });

        return template;

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
