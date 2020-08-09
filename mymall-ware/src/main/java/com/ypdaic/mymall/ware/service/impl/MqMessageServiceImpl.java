package com.ypdaic.mymall.ware.service.impl;

import com.ypdaic.mymall.ware.entity.MqMessage;
import com.ypdaic.mymall.ware.mapper.MqMessageMapper;
import com.ypdaic.mymall.ware.service.IMqMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.MqMessageDto;
import com.ypdaic.mymall.ware.enums.MqMessageExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import java.util.Date;
import java.util.Date;

/**
 * <p>
 * 消息备份 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage> implements IMqMessageService {


    /**
     * 新增消息备份
     * @param mqMessageDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MqMessage add(MqMessageDto mqMessageDto) {

        MqMessage mqMessage = new MqMessage();
        mqMessage.setMessageId(mqMessageDto.getMessageId());
        mqMessage.setMessageType(mqMessageDto.getMessageType());
        mqMessage.setContent(mqMessageDto.getContent());
        mqMessage.setToExchange(mqMessageDto.getToExchange());
        mqMessage.setRoutingKey(mqMessageDto.getRoutingKey());
        mqMessage.setClassType(mqMessageDto.getClassType());
        mqMessage.setMessageStatus(mqMessageDto.getMessageStatus());
        Date date7= new Date();
        mqMessage.setCreateTime(date7);
        Date date8= new Date();
        mqMessage.setUpdateTime(date8);
        mqMessage.insert();
        return mqMessage;
    }

    /**
     * 更新消息备份
     * @param mqMessageDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MqMessage update(MqMessageDto mqMessageDto) {
        MqMessage mqMessage = baseMapper.selectById(mqMessageDto.getId());
        if (mqMessage == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getMessageId(), mqMessage::setMessageId);
        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getMessageType(), mqMessage::setMessageType);
        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getContent(), mqMessage::setContent);
        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getToExchange(), mqMessage::setToExchange);
        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getRoutingKey(), mqMessage::setRoutingKey);
        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getClassType(), mqMessage::setClassType);
        JavaUtils.INSTANCE.acceptIfNotNull(mqMessageDto.getMessageStatus(), mqMessage::setMessageStatus);
        Date date8= new Date();
        mqMessage.setUpdateTime(date8);
        mqMessage.updateById();
        return mqMessage;
    }

    /**
     * 删除消息备份
     * @param mqMessageDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MqMessage delete(MqMessageDto mqMessageDto) {
        MqMessage mqMessage = baseMapper.selectById(mqMessageDto.getId());
        if (mqMessage == null) {
            return null;
        }
        mqMessage.deleteById();

        return mqMessage;
    }

    /**
     * 分页查询消息备份
     * @param mqMessageDto
     * @param mqMessagePage
     * @return
     */
    @Override
    public IPage<MqMessage> queryPage(MqMessageDto mqMessageDto, Page<MqMessage> mqMessagePage) {

        return baseMapper.queryPage(mqMessagePage, mqMessageDto);
    }


    /**
     * 校验消息备份名称
     * @param mqMessageDto
     * @return
     */
    @Override
    public boolean checkName(MqMessageDto mqMessageDto, boolean isAdd) {

        QueryWrapper<MqMessage> mqMessageQueryWrapper = new QueryWrapper<MqMessage>();
        mqMessageQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        mqMessageQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            mqMessageQueryWrapper.ne("id", mqMessageDto.getId());
        }

        Integer count = baseMapper.selectCount(mqMessageQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有消息备份
     * @return
     */
    @Override
    public List<MqMessage> queryAll(MqMessageDto mqMessageDto) {
        return baseMapper.queryAll(mqMessageDto);
    }

}
