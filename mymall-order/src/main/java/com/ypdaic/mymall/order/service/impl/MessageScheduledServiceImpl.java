package com.ypdaic.mymall.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypdaic.mymall.order.entity.MqMessage;
import com.ypdaic.mymall.order.enums.MqMessageExcelHeadersEnum;
import com.ypdaic.mymall.order.service.IMessageScheduled;
import com.ypdaic.mymall.order.service.IMqMessageService;
import com.ypdaic.mymall.order.vo.MqMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageScheduledServiceImpl implements IMessageScheduled {

    @Autowired
    IMqMessageService mqMessageService;

    @Override
    public List<MqMessage> handleNoConsumerMessage(MqMessage.MessageType messageType) {
        Page<MqMessage> mqMessagePage = new Page<>();
        mqMessagePage.setSize(1000);
        mqMessagePage.setCurrent(1);
        MqMessageDto mqMessageDto = new MqMessageDto();
        mqMessageDto.setMessageStatus(MqMessage.MessageStatus.NO_CONSUME.getValue());
        mqMessageDto.setMessageType(messageType.getValue());
        IPage<MqMessage> mqMessageIPage = mqMessageService.queryPage(mqMessageDto, mqMessagePage);
        if (Objects.nonNull(mqMessagePage)) {
            List<MqMessage> records = mqMessageIPage.getRecords();
            return records;
        }
        return new ArrayList<>();
    }
}
