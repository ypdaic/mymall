package com.ypdaic.mymall.order.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.to.mq.StockLockedTo;
import com.ypdaic.mymall.order.entity.MqMessage;
import com.ypdaic.mymall.order.entity.Order;
import com.ypdaic.mymall.order.service.IMqMessageService;
import com.ypdaic.mymall.order.service.IOrderService;
import com.ypdaic.mymall.order.vo.MqMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class OrderCloseListener {

    @Autowired
    IOrderService orderService;

    @Autowired
    IMqMessageService mqMessageService;

    /**
     * 监听过期队列的消息
     * @param string
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "order.release.queue")
    public void handleOrderClose(Order order, Message message, Channel channel) throws IOException {
//        Order order = JSONObject.parseObject(string, Order.class);
        log.info("收到过期的订单信息：准备关闭订单：{}", order.getOrderSn() );
        try {
            handleTransaction(order, message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }



    }

    @Transactional(rollbackFor = Exception.class)
    public void handleTransaction(Order order, Message message) {
        orderService.closeOrder(order);
        updateMessageStatus(message);
    }

    private void updateMessageStatus(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String messageId = messageProperties.getMessageId();
        if (StringUtils.isNotEmpty(messageId)) {
            MqMessageDto mqMessageDto = new MqMessageDto();
            mqMessageDto.setMessageId(messageId);
            List<MqMessage> mqMessages = mqMessageService.queryAll(mqMessageDto);
            if (CollectionUtils.isNotEmpty(mqMessages)) {
                MqMessage mqMessage = mqMessages.get(0);
                mqMessage.setMessageStatus(MqMessage.MessageStatus.CONSUME.getValue());
                mqMessageService.updateById(mqMessage);
            }
        }

    }
}
