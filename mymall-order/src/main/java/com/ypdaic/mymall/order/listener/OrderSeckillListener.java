package com.ypdaic.mymall.order.listener;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.to.SeckillOrderTo;
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
public class OrderSeckillListener {

    @Autowired
    IOrderService orderService;

    /**
     * 处理秒杀订单消息
     * @param SeckillOrderTo
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "order.seckill.order.queue")
    public void handleSeckillOrder(SeckillOrderTo seckillOrderTo, Message message, Channel channel) throws IOException {
//        Order order = JSONObject.parseObject(string, Order.class);
        log.info("收到秒杀单信息：{}", seckillOrderTo );
        try {
            orderService.createSeckillOrder(seckillOrderTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }



    }
}
