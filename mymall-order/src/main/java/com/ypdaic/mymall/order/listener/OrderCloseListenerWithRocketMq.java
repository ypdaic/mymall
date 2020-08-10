package com.ypdaic.mymall.order.listener;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.order.entity.MqMessage;
import com.ypdaic.mymall.order.entity.Order;
import com.ypdaic.mymall.order.service.IMqMessageService;
import com.ypdaic.mymall.order.service.IOrderService;
import com.ypdaic.mymall.order.vo.MqMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test", consumerGroup = "test", enableMsgTrace = false)
public class OrderCloseListenerWithRocketMq implements RocketMQListener {

    @Autowired
    IOrderService orderService;

    @Autowired
    IMqMessageService mqMessageService;

    @Transactional(rollbackFor = Exception.class)
    public void handleTransaction(Order order) {
        orderService.closeOrder(order);
    }


    @Override
    public void onMessage(Object o) {
        Order order = (Order) o;
        log.info("收到过期的订单信息：准备关闭订单：{}", order.getOrderSn() );
        try {
            handleTransaction(order);

        } catch (Exception e) {

        }
    }
}
