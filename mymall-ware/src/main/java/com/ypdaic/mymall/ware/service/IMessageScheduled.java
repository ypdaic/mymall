package com.ypdaic.mymall.ware.service;



import com.ypdaic.mymall.ware.entity.MqMessage;

import java.util.List;

public interface IMessageScheduled {

    /**
     * 处理没有被消费的消息
     * @return
     */
    List<MqMessage> handleNoConsumerMessage(MqMessage.MessageType messageType);
}
