package com.ypdaic.mymall.ware.listener;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.to.mq.OrderTo;
import com.ypdaic.mymall.common.to.mq.StockLockedTo;
import com.ypdaic.mymall.ware.service.IWareSkuService;
import com.ypdaic.mymall.ware.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {

    @Autowired
    IWareSkuService wareSkuService;

    /**
     * 主动解锁
     * @param stockLockedTo
     * @param message
     * @param channel
     */
    @RabbitHandler
    public void handleStockRelese(StockLockedTo stockLockedTo, org.springframework.amqp.core.Message message, com.rabbitmq.client.Channel channel) {
        log.info("收到解锁库存的消息:{}", message.getMessageProperties().getDeliveryTag());
        try {
            wareSkuService.releaseLockStock(stockLockedTo);
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

    /**
     * 被动解锁
     * @param orderTo
     * @param message
     * @param channel
     */
    @RabbitHandler
    public void handleOrderCloseRelease(OrderTo orderTo, Message message, Channel channel) {
        log.info("订单关闭准备解锁");
        try {
            wareSkuService.unLockStock(orderTo);
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
}
