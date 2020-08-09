package com.ypdaic.mymall.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.enums.MessageStatusEnum;
import com.ypdaic.mymall.common.enums.PublicEnum;
import com.ypdaic.mymall.common.exception.MessageBizException;
import com.ypdaic.mymall.common.util.JavaUtils;
import com.ypdaic.mymall.message.entity.RpTransactionMessage;
import com.ypdaic.mymall.message.mapper.RpTransactionMessageMapper;
import com.ypdaic.mymall.message.service.IRpTransactionMessageService;
import com.ypdaic.mymall.message.vo.RpTransactionMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 *
 *  异常情况总结及处理：
 *
 *      消息未进存储，业务操作未执行      一致
 *      消息进了存储（状态待确认），业务操作未执行     不一致         确认业务操作结果，处理消息（删除消息）
 *      消息进了存储（状态待确认），业务操作成功       不一致         确认消息操作结果，处理消息（更新消息状态，执行消息投递）
 *
 *      消息进了存储（状态为发送中），但是没有被消费   不一致         重新发送消息
 *
 *  消息正常消费后，删除消息
 * </p>
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@Service
@Validated
@Slf4j
public class RpTransactionMessageServiceImpl extends ServiceImpl<RpTransactionMessageMapper, RpTransactionMessage> implements IRpTransactionMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${message.max.send.times}")
    private Integer messageMaxTimes;

    /**
     * 新增
     * @param rpTransactionMessageDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RpTransactionMessage add(RpTransactionMessageDto rpTransactionMessageDto) {

        RpTransactionMessage rpTransactionMessage = new RpTransactionMessage();
        rpTransactionMessage.setVersion(rpTransactionMessageDto.getVersion());
        rpTransactionMessage.setEditor(rpTransactionMessageDto.getEditor());
        rpTransactionMessage.setCreater(rpTransactionMessageDto.getCreater());
        Date date3= new Date();
        rpTransactionMessage.setEditTime(date3);
        Date date4= new Date();
        rpTransactionMessage.setCreateTime(date4);
        rpTransactionMessage.setMessageId(rpTransactionMessageDto.getMessageId());
        rpTransactionMessage.setMessageBody(rpTransactionMessageDto.getMessageBody());
        rpTransactionMessage.setMessageDataType(rpTransactionMessageDto.getMessageDataType());
        rpTransactionMessage.setConsumerQueue(rpTransactionMessageDto.getConsumerQueue());
        rpTransactionMessage.setMessageSendTimes(rpTransactionMessageDto.getMessageSendTimes());
        rpTransactionMessage.setAreadlyDead(rpTransactionMessageDto.getAreadlyDead());
        rpTransactionMessage.setStatus(rpTransactionMessageDto.getStatus());
        rpTransactionMessage.setRemark(rpTransactionMessageDto.getRemark());
        rpTransactionMessage.setField1(rpTransactionMessageDto.getField1());
        rpTransactionMessage.setField2(rpTransactionMessageDto.getField2());
        rpTransactionMessage.setField3(rpTransactionMessageDto.getField3());
        rpTransactionMessage.insert();
        return rpTransactionMessage;
    }

    /**
     * 更新
     * @param rpTransactionMessageDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RpTransactionMessage update(RpTransactionMessageDto rpTransactionMessageDto) {
        RpTransactionMessage rpTransactionMessage = baseMapper.selectById(rpTransactionMessageDto.getId());
        if (rpTransactionMessage == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getVersion(), rpTransactionMessage::setVersion);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getEditor(), rpTransactionMessage::setEditor);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getCreater(), rpTransactionMessage::setCreater);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getEditTime(), rpTransactionMessage::setEditTime);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getMessageId(), rpTransactionMessage::setMessageId);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getMessageBody(), rpTransactionMessage::setMessageBody);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getMessageDataType(), rpTransactionMessage::setMessageDataType);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getConsumerQueue(), rpTransactionMessage::setConsumerQueue);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getMessageSendTimes(), rpTransactionMessage::setMessageSendTimes);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getAreadlyDead(), rpTransactionMessage::setAreadlyDead);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getStatus(), rpTransactionMessage::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getRemark(), rpTransactionMessage::setRemark);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getField1(), rpTransactionMessage::setField1);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getField2(), rpTransactionMessage::setField2);
        JavaUtils.INSTANCE.acceptIfNotNull(rpTransactionMessageDto.getField3(), rpTransactionMessage::setField3);
        rpTransactionMessage.updateById();
        return rpTransactionMessage;
    }

    /**
     * 删除
     * @param rpTransactionMessageDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RpTransactionMessage delete(RpTransactionMessageDto rpTransactionMessageDto) {
        RpTransactionMessage rpTransactionMessage = baseMapper.selectById(rpTransactionMessageDto.getId());
        if (rpTransactionMessage == null) {
            return null;
        }
        rpTransactionMessage.deleteById();

        return rpTransactionMessage;
    }

    /**
     * 分页查询
     * @param rpTransactionMessageDto
     * @param rpTransactionMessagePage
     * @return
     */
    @Override
    public IPage<RpTransactionMessage> queryPage(RpTransactionMessageDto rpTransactionMessageDto, Page<RpTransactionMessage> rpTransactionMessagePage) {

        return baseMapper.queryPage(rpTransactionMessagePage, rpTransactionMessageDto);
    }


    /**
     * 校验名称
     * @param rpTransactionMessageDto
     * @return
     */
    @Override
    public boolean checkName(RpTransactionMessageDto rpTransactionMessageDto, boolean isAdd) {

        QueryWrapper<RpTransactionMessage> rpTransactionMessageQueryWrapper = new QueryWrapper<RpTransactionMessage>();
        rpTransactionMessageQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        rpTransactionMessageQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            rpTransactionMessageQueryWrapper.ne("id", rpTransactionMessageDto.getId());
        }

        Integer count = baseMapper.selectCount(rpTransactionMessageQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有
     * @return
     */
    @Override
    public List<RpTransactionMessage> queryAll(RpTransactionMessageDto rpTransactionMessageDto) {
        return baseMapper.queryAll(rpTransactionMessageDto);
    }

    /**
     * 预存储消息
     * 异常情况
     *     预发送消息失败，消息未进存储，业务操作未执行（可能的原因：主动方应用，网络，消息中间件，消息存储） 一致
     *     预发送消息后，主动方应用没有收到返回的消息存储结果
     *                  可能的状态：
     *                      消息未进存储，业务操作未执行，  一致
     *                      消息一进存储（待确认），业务操作未执行 不一致
     *
     *     收到消息存储成功的放回结果，但未执行业务操作，
     *                  可能的状态：
     *                      消息已进存储，业务操作未执行（待确认）
     *
     *
     */
    public int saveMessageWaitingConfirm(@Validated RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

//        if (StringUtils.isEmpty(message.getConsumerQueue()) && StringUtils.isEmpty(message.getExchange())) {
//            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
//        }

        if (StringUtils.isEmpty(message.getExchange()) && StringUtils.isEmpty(message.getRoutingKey())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息队列交换器不能为空 ");
        }

        message.setCreateTime(new Date());
        message.setEditTime(new Date());
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
        message.setAreadlyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        return baseMapper.insert(message);
    }

    /**
     * 确认并发送消息.
     * 异常情况：
     *
     *         没有收到主动应用的业务操作处理结果
     *                      可能的状态：
     *                           消息已进存储（待确认），业务操作未执行    不一致
     *                           消息已进存储（待确认），业务操作成功      不一致
     *
     *        收到主动应用的业务操作处理结果
     *                      可能的状态：
     *                           消息已进存储（待确认），业务操作未执行    不一致
     *                           消息已进存储（待确认），业务操作成功      不一致
     */
    public void confirmAndSendMessage(String messageId) {
        final RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setEditTime(new Date());
        baseMapper.updateById(message);

//        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
//        notifyJmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message.getMessageBody());
//            }
//        });
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(messageId);
        rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message, correlationData);
    }

    /**
     * 存储并发送消息.
     */
    public int saveAndSendMessage(final RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setAreadlyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        message.setEditTime(new Date());
        int result = baseMapper.insert(message);

//        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
//        notifyJmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message.getMessageBody());
//            }
//        });

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageId());
        rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message, correlationData);

        return result;
    }

    /**
     * 直接发送消息.
     */
    public void directSendMessage(final RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

//        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
//        notifyJmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message.getMessageBody());
//            }
//        });
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageId());
        rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message, correlationData);
    }

    /**
     * 重发消息.
     */
    public void reSendMessage(final RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.addSendTimes();
        message.setEditTime(new Date());
        baseMapper.updateById(message);

//        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
//        notifyJmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message.getMessageBody());
//            }
//        });

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageId());
        rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message, correlationData);
    }

    /**
     * 根据messageId重发某条消息.
     */
    public void reSendMessageByMessageId(String messageId) {
        final RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        if (message.getMessageSendTimes() >= messageMaxTimes) {
            message.setAreadlyDead(PublicEnum.YES.name());
        }

        message.setEditTime(new Date());
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        baseMapper.updateById(message);

//        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
//        notifyJmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message.getMessageBody());
//            }
//        });
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageId());
        rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(), message, correlationData);
    }

    /**
     * 将消息标记为死亡消息.
     */
    public void setMessageToAreadlyDead(String messageId) {
        RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        message.setAreadlyDead(PublicEnum.YES.name());
        message.setEditTime(new Date());
        baseMapper.updateById(message);
    }

    /**
     * 根据消息ID获取消息
     */
    public RpTransactionMessage getMessageByMessageId(String messageId) {

        QueryWrapper<RpTransactionMessage> messageQueryWrapper = new QueryWrapper<RpTransactionMessage>();
        messageQueryWrapper.eq("message_id", messageId);
        return baseMapper.selectOne(messageQueryWrapper);
    }

    /**
     * 根据消息ID删除消息
     */
    public void deleteMessageByMessageId(String messageId) {
        QueryWrapper<RpTransactionMessage> messageQueryWrapper = new QueryWrapper<RpTransactionMessage>();
        messageQueryWrapper.eq("message_id", messageId);
        RpTransactionMessage rpTransactionMessage = baseMapper.selectOne(messageQueryWrapper);
        rpTransactionMessage.deleteById();
    }

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @SuppressWarnings("unchecked")
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) {
        log.info("==>reSendAllDeadMessageByQueueName");

//        int numPerPage = 1000;
//        if (batchSize > 0 && batchSize < 100){
//            numPerPage = 100;
//        } else if (batchSize > 100 && batchSize < 5000){
//            numPerPage = batchSize;
//        } else if (batchSize > 5000){
//            numPerPage = 5000;
//        } else {
//            numPerPage = 1000;
//        }
//
//        int pageNum = 1;
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("consumerQueue", queueName);
//        paramMap.put("areadlyDead", PublicEnum.YES.name());
//        paramMap.put("listPageSortType", "ASC");
//
//
//        Map<String, RpTransactionMessage> messageMap = new HashMap<String, RpTransactionMessage>();
//        List<Object> recordList = new ArrayList<Object>();
//        int pageCount = 1;
//
//        PageBean pageBean = rpTransactionMessageDao.listPage(new PageParam(pageNum, numPerPage), paramMap);
//        recordList = pageBean.getRecordList();
//        if (recordList == null || recordList.isEmpty()) {
//            log.info("==>recordList is empty");
//            return;
//        }
//        pageCount = pageBean.getTotalPage();
//        for (final Object obj : recordList) {
//            final RpTransactionMessage message = (RpTransactionMessage) obj;
//            messageMap.put(message.getMessageId(), message);
//        }
//
//        for (pageNum = 2; pageNum <= pageCount; pageNum++) {
//            pageBean = rpTransactionMessageDao.listPage(new PageParam(pageNum, numPerPage), paramMap);
//            recordList = pageBean.getRecordList();
//
//            if (recordList == null || recordList.isEmpty()) {
//                break;
//            }
//
//            for (final Object obj : recordList) {
//                final RpTransactionMessage message = (RpTransactionMessage) obj;
//                messageMap.put(message.getMessageId(), message);
//            }
//        }
//
//        recordList = null;
//        pageBean = null;
//
//        for (Map.Entry<String, RpTransactionMessage> entry : messageMap.entrySet()) {
//            final RpTransactionMessage message = entry.getValue();
//
//            message.setEditTime(new Date());
//            message.setMessageSendTimes(message.getMessageSendTimes() + 1);
//            rpTransactionMessageDao.update(message);
//
//            notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
//            notifyJmsTemplate.send(new MessageCreator() {
//                public Message createMessage(Session session) throws JMSException {
//                    return session.createTextMessage(message.getMessageBody());
//                }
//            });
//        }

    }


//    @SuppressWarnings("unchecked")
//    public PageBean<RpTransactionMessage> listPage(PageParam pageParam, Map<String, Object> paramMap){
//        return rpTransactionMessageDao.listPage(pageParam, paramMap);
//    }

}
