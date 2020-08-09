package com.ypdaic.mymall.message.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypdaic.mymall.common.enums.MessageStatusEnum;
import com.ypdaic.mymall.common.enums.PublicEnum;
import com.ypdaic.mymall.message.biz.MessageBiz;
import com.ypdaic.mymall.message.entity.RpTransactionMessage;
import com.ypdaic.mymall.message.service.IMessageScheduled;
import com.ypdaic.mymall.message.service.IRpTransactionMessageService;
import com.ypdaic.mymall.message.vo.RpTransactionMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageScheduledImpl implements IMessageScheduled {

    @Autowired
    private IRpTransactionMessageService rpTransactionMessageService;

    // 和业务相关的回调
    @Autowired
    private MessageBiz messageBiz;

    @Value("${message.handle.duration}")
    private String messageTimeout;

    /**
     * 处理状态为“待确认”但已超时的消息.
     */
    public void handleWaitingConfirmTimeOutMessages() {
        try {

            // 获取3分钟前的消息
            Date date = new Date(System.currentTimeMillis() - Integer.valueOf(messageTimeout) * 1000);
            Page<RpTransactionMessage> rpTransactionMessagePage = new Page<>(1, 2000);
            RpTransactionMessageDto rpTransactionMessageDto = new RpTransactionMessageDto();
            rpTransactionMessageDto.setStatus(MessageStatusEnum.WAITING_CONFIRM.name()); // 取状态为“待确认”的消息
            rpTransactionMessageDto.setCreateTime(date);
            IPage<RpTransactionMessage> rpTransactionMessageIPage = rpTransactionMessageService.queryPage(rpTransactionMessageDto, rpTransactionMessagePage);
            HashMap<String, RpTransactionMessage> messageMap = new HashMap<>();
            if (Objects.nonNull(rpTransactionMessageIPage) && CollectionUtils.isNotEmpty(rpTransactionMessageIPage.getRecords())) {
                List<RpTransactionMessage> records = rpTransactionMessageIPage.getRecords();
                Map<String, RpTransactionMessage> collect = records.stream().collect(Collectors.toMap(RpTransactionMessage::getMessageId, rpTransactionMessage -> rpTransactionMessage));
                messageMap.putAll(collect);
                long total = rpTransactionMessageIPage.getTotal();
                if (total > 3) {
                    total = 3;
                    for (int i = 2; i < total + 1; i++) {
                        rpTransactionMessagePage.setCurrent(i);
                        rpTransactionMessageIPage = rpTransactionMessageService.queryPage(rpTransactionMessageDto, rpTransactionMessagePage);
                        if (Objects.nonNull(rpTransactionMessageIPage) && CollectionUtils.isNotEmpty(rpTransactionMessageIPage.getRecords())) {

                            records = rpTransactionMessageIPage.getRecords();
                            collect = records.stream().collect(Collectors.toMap(RpTransactionMessage::getMessageId, rpTransactionMessage -> rpTransactionMessage));
                            messageMap.putAll(collect);
                        }
                    }
                }
            }
//            Map<String, RpTransactionMessage> messageMap = getMessageMap(numPerPage, maxHandlePageCount, paramMap);

            messageBiz.handleWaitingConfirmTimeOutMessages(messageMap);

        } catch (Exception e) {
            log.error("处理[waiting_confirm]状态的消息异常" + e);
        }
    }


    /**
     * 处理状态为“发送中”但超时没有被成功消费确认的消息
     */
    public void handleSendingTimeOutMessage() {
        try {

            // 获取3分钟前的消息
            Date date = new Date(System.currentTimeMillis() - Integer.valueOf(messageTimeout) * 1000);
            Page<RpTransactionMessage> rpTransactionMessagePage = new Page<>(1, 2000);
            RpTransactionMessageDto rpTransactionMessageDto = new RpTransactionMessageDto();
            rpTransactionMessageDto.setStatus(MessageStatusEnum.SENDING.name()); // 取状态为“发送中”的消息
            rpTransactionMessageDto.setAreadlyDead(PublicEnum.NO.name()); // 取存活的发送中消息
            rpTransactionMessageDto.setCreateTime(date);
            IPage<RpTransactionMessage> rpTransactionMessageIPage = rpTransactionMessageService.queryPage(rpTransactionMessageDto, rpTransactionMessagePage);
            HashMap<String, RpTransactionMessage> messageMap = new HashMap<>();
            if (Objects.nonNull(rpTransactionMessageIPage) && CollectionUtils.isNotEmpty(rpTransactionMessageIPage.getRecords())) {
                List<RpTransactionMessage> records = rpTransactionMessageIPage.getRecords();
                Map<String, RpTransactionMessage> collect = records.stream().collect(Collectors.toMap(RpTransactionMessage::getMessageId, rpTransactionMessage -> rpTransactionMessage));
                messageMap.putAll(collect);
                long total = rpTransactionMessageIPage.getTotal();
                if (total > 3) {
                    total = 3;
                    for (int i = 2; i < total + 1; i++) {
                        rpTransactionMessagePage.setCurrent(i);
                        rpTransactionMessageIPage = rpTransactionMessageService.queryPage(rpTransactionMessageDto, rpTransactionMessagePage);
                        if (Objects.nonNull(rpTransactionMessageIPage) && CollectionUtils.isNotEmpty(rpTransactionMessageIPage.getRecords())) {
                            records = rpTransactionMessageIPage.getRecords();
                            collect = records.stream().collect(Collectors.toMap(RpTransactionMessage::getMessageId, rpTransactionMessage -> rpTransactionMessage));
                            messageMap.putAll(collect);
                        }

                    }
                }
            }
//            Map<String, RpTransactionMessage> messageMap = getMessageMap(numPerPage, maxHandlePageCount, paramMap);

//            messageBiz.handleSendingTimeOutMessage(messageMap);
        } catch (Exception e) {
            log.error("处理发送中的消息异常" + e);
        }
    }

//    /**
//     * 根据分页参数及查询条件批量获取消息数据.
//     * @param numPerPage 每页记录数.
//     * @param maxHandlePageCount 最多获取页数.
//     * @param paramMap 查询参数.
//     * @return
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    private Map<String, RpTransactionMessage> getMessageMap(int numPerPage, int maxHandlePageCount, Map<String, Object> paramMap){
//
//        int pageNum = 1; // 当前页
//
//        Map<String, RpTransactionMessage> messageMap = new HashMap<String, RpTransactionMessage>(); // 转换成map
//        List<RpTransactionMessage> recordList = new ArrayList<RpTransactionMessage>(); // 每次拿到的结果集
//        int pageCount = 1; // 总页数
//
//        log.info("==>pageNum:" + pageNum + ", numPerPage:" + numPerPage);
//        PageBean pageBean = rpTransactionMessageService.listPage(new PageParam(pageNum, numPerPage), paramMap);
//        recordList = pageBean.getRecordList();
//        if (recordList == null || recordList.isEmpty()) {
//            log.info("==>recordList is empty");
//            return messageMap;
//        }
//        log.info("==>now page size:" + recordList.size());
//
//        for (RpTransactionMessage message : recordList) {
//            messageMap.put(message.getMessageId(), message);
//        }
//
//        pageCount = pageBean.getTotalPage(); // 总页数(可以通过这个值的判断来控制最多取多少页)
//        log.info("==>pageCount:" + pageCount);
//        if (pageCount > maxHandlePageCount){
//            pageCount = maxHandlePageCount;
//            log.info("==>set pageCount:" + pageCount);
//        }
//
//        for (pageNum = 2; pageNum <= pageCount; pageNum++) {
//            log.info("==>pageNum:" + pageNum + ", numPerPage:" + numPerPage);
//            pageBean = rpTransactionMessageService.listPage(new PageParam(pageNum, numPerPage), paramMap);
//            recordList = pageBean.getRecordList();
//            if (recordList == null || recordList.isEmpty()) {
//                break;
//            }
//            log.info("==>now page size:" + recordList.size());
//
//            for (RpTransactionMessage message : recordList) {
//                messageMap.put(message.getMessageId(), message);
//            }
//        }
//
//        recordList = null;
//        pageBean = null;
//
//        return messageMap;
//    }

//    /**
//     * 获取配置的开始处理的时间
//     *
//     * @return
//     */
//    private String getCreateTimeBefore() {
////        String duration = PublicConfigUtil.readConfig("message.handle.duration");
//        long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
//        Date date = new Date(currentTimeInMillis - Integer.valueOf(messageTimeout) * 1000);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = sdf.format(date);
//        return dateStr;
//    }

}
