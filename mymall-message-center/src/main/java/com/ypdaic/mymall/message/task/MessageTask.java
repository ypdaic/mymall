package com.ypdaic.mymall.message.task;

import com.ypdaic.mymall.message.service.IMessageScheduled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageTask {

    @Autowired
    IMessageScheduled messageScheduled;

    /**
     * 待确认” 但已超时的消息.
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void handle() {
        log.info("执行(处理[waiting_confirm]状态的消息)任务开始");
        messageScheduled.handleWaitingConfirmTimeOutMessages();
        log.info("执行(处理[waiting_confirm]状态的消息)任务结束");
    }

    /**
     * “发送中”但超时没有被成功消费确认的消息
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void handle2() {
        log.info("执行(处理[SENDING]的消息)任务开始");
        messageScheduled.handleSendingTimeOutMessage();
        log.info("执行(处理[SENDING]的消息)任务结束");
    }

}
