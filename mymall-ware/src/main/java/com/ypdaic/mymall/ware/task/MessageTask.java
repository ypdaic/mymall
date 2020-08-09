package com.ypdaic.mymall.ware.task;

import com.ypdaic.mymall.ware.entity.MqMessage;
import com.ypdaic.mymall.ware.service.IMessageScheduled;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;
import static org.springframework.amqp.support.converter.DefaultClassMapper.DEFAULT_CLASSID_FIELD_NAME;

@Service
@Slf4j
public class MessageTask {

    @Autowired
    IMessageScheduled messageScheduled;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MessageConverter messageConverter;

    /**
     * 处理非延时没有被消费的消息
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void handle() {
        log.info("执行(处理[NO_CONSUMER]状态的消息)任务开始");
        List<MqMessage> mqMessages = messageScheduled.handleNoConsumerMessage(MqMessage.MessageType.NOW);
        if (CollectionUtils.isNotEmpty(mqMessages)) {
            mqMessages.forEach(mqMessage -> {
                MessageProperties messageProperties = new MessageProperties();
                messageProperties.setContentType(CONTENT_TYPE_JSON);
                messageProperties.setMessageId(mqMessage.getMessageId());
                messageProperties.getHeaders().put(DEFAULT_CLASSID_FIELD_NAME, mqMessage.getClassType());
                Message message = new Message(mqMessage.getContent().getBytes(), messageProperties);
//                MessageProperties messageProperties = new MessageProperties();
//                messageProperties.setMessageId(mqMessage.getMessageId());
//                Message message = messageConverter.toMessage(mqMessage.getContent(), messageProperties);
                rabbitTemplate.convertAndSend(mqMessage.getToExchange(), mqMessage.getRoutingKey(), message);
            });
        }
        log.info("执行(处理[NO_CONSUMER]状态的消息)任务结束");
    }

    /**
     * 处理延时没有被消费的消息
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void handleNowMessage() {
        log.info("执行(处理[NO_CONSUMER]状态的消息)任务开始");
        List<MqMessage> mqMessages = messageScheduled.handleNoConsumerMessage(MqMessage.MessageType.DELAY);
        if (CollectionUtils.isNotEmpty(mqMessages)) {
            mqMessages.forEach(mqMessage -> {
                MessageProperties messageProperties = new MessageProperties();
                messageProperties.setContentType(CONTENT_TYPE_JSON);
                messageProperties.setMessageId(mqMessage.getMessageId());
                messageProperties.getHeaders().put(DEFAULT_CLASSID_FIELD_NAME, mqMessage.getClassType());
                Message message = new Message(mqMessage.getContent().getBytes(), messageProperties);
//                MessageProperties messageProperties = new MessageProperties();
//                messageProperties.setMessageId(mqMessage.getMessageId());
//                Message message = messageConverter.toMessage(mqMessage.getContent(), messageProperties);
                rabbitTemplate.convertAndSend(mqMessage.getToExchange(), mqMessage.getRoutingKey(), message);
            });
        }
        log.info("执行(处理[NO_CONSUMER]状态的消息)任务结束");
    }

}
