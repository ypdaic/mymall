package com.ypdaic.mymall.ware.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.to.mq.OrderTo;
import com.ypdaic.mymall.common.to.mq.StockLockedTo;
import com.ypdaic.mymall.ware.entity.MqMessage;
import com.ypdaic.mymall.ware.service.IMqMessageService;
import com.ypdaic.mymall.ware.service.IWareSkuService;
import com.ypdaic.mymall.ware.vo.MqMessageDto;
import com.ypdaic.mymall.ware.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {

    @Autowired
    IWareSkuService wareSkuService;

    @Autowired
    IMqMessageService mqMessageService;

    /**
     * 主动解锁
     * @param stockLockedTo
     * @param message
     * @param channel
     */
    @RabbitHandler
    public void handleStockRelese(StockLockedTo stockLockedTo, org.springframework.amqp.core.Message message, com.rabbitmq.client.Channel channel) {
//        StockLockedTo stockLockedTo = JSONObject.parseObject(string, StockLockedTo.class);
        log.info("收到解锁库存的消息:{}", message.getMessageProperties().getDeliveryTag());
        try {
            handleTransaction(stockLockedTo, message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            log.error("业务执行异常", e);
            // 远程调用失败，需要拒绝消息，下次再进行消费
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                log.error("消息拒绝失败");
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void handleTransaction(StockLockedTo stockLockedTo, Message message) {
        wareSkuService.releaseLockStock(stockLockedTo);
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

    /**
     * 被动解锁
     * @param orderTo
     * @param message
     * @param channel
     */
    @RabbitHandler
    public void handleOrderCloseRelease(OrderTo orderTo, Message message, Channel channel) {
//        OrderTo orderTo = JSONObject.parseObject(string, OrderTo.class);
        log.info("订单关闭准备解锁");
        try {
            handleTransaction2(orderTo, message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("业务执行异常", e);
            // 远程调用失败，需要拒绝消息，下次再进行消费
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ex) {
                log.error("消息拒绝失败");
            }
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void handleTransaction2(OrderTo orderTo, Message message) {
        wareSkuService.unLockStock(orderTo);
        updateMessageStatus(message);
    }
}
