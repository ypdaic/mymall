package com.ypdaic.mymall.order.listener;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.order.entity.Order;
import com.ypdaic.mymall.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OrderCloseListener {

    @Autowired
    IOrderService orderService;

    @RabbitListener(queues = "order.release.queue")
    public void handleOrderClose(Order order, Message message, Channel channel) throws IOException {
        log.info("收到过期的订单信息：准备关闭订单：{}", order.getOrderSn() );
        try {
            orderService.closeOrder(order);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }



    }
}
