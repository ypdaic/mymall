package com.ypdaic.mymall.message.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.common.exception.MessageBizException;
import com.ypdaic.mymall.message.entity.RpTransactionMessage;
import com.ypdaic.mymall.message.vo.RpTransactionMessageDto;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-08-08
 */
public interface IRpTransactionMessageService extends IService<RpTransactionMessage> {

    /**
     * 新增
     * @param rpTransactionMessageDto
     * @return
     */
    RpTransactionMessage add(RpTransactionMessageDto rpTransactionMessageDto);

    /**
     * 更新
     * @param rpTransactionMessageDto
     * @return
     */
    RpTransactionMessage update(RpTransactionMessageDto rpTransactionMessageDto);

    /**
     * 删除
     * @param rpTransactionMessageDto
     * @return
     */
    RpTransactionMessage delete(RpTransactionMessageDto rpTransactionMessageDto);

    /**
     * 分页查询
     * @param rpTransactionMessageDto
     * @param rpTransactionMessagePage
     * @return
     */
    IPage<RpTransactionMessage> queryPage(RpTransactionMessageDto rpTransactionMessageDto, Page<RpTransactionMessage> rpTransactionMessagePage);


    /**
     * 校验名称
     * @param rpTransactionMessageDto
     * @return
     */
    boolean checkName(RpTransactionMessageDto rpTransactionMessageDto, boolean isAdd);

    /**
     *
     * 查询所有
     * @return
     */
    List<RpTransactionMessage> queryAll(RpTransactionMessageDto rpTransactionMessageDto);

    /**
     * 预存储消息.
     */
    public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 确认并发送消息.
     */
    public void confirmAndSendMessage(String messageId) throws MessageBizException;


    /**
     * 存储并发送消息.
     */
    public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 直接发送消息.
     */
    public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 重发消息.
     */
    public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 根据messageId重发某条消息.
     */
    public void reSendMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 将消息标记为死亡消息.
     */
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException;


    /**
     * 根据消息ID获取消息
     */
    public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * 根据消息ID删除消息
     */
    public void deleteMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException;

//    /**
//     * 获取分页数据
//     */
//    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) throws MessageBizException;
}
