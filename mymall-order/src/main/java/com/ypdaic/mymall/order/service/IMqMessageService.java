package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.order.entity.MqMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.MqMessageDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 消息备份 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-08-08
 */
public interface IMqMessageService extends IService<MqMessage> {

    /**
     * 新增消息备份
     * @param mqMessageDto
     * @return
     */
    MqMessage add(MqMessageDto mqMessageDto);

    /**
     * 更新消息备份
     * @param mqMessageDto
     * @return
     */
    MqMessage update(MqMessageDto mqMessageDto);

    /**
     * 删除消息备份
     * @param mqMessageDto
     * @return
     */
    MqMessage delete(MqMessageDto mqMessageDto);

    /**
     * 分页查询消息备份
     * @param mqMessageDto
     * @param mqMessagePage
     * @return
     */
    IPage<MqMessage> queryPage(MqMessageDto mqMessageDto, Page<MqMessage> mqMessagePage);


    /**
     * 校验消息备份名称
     * @param mqMessageDto
     * @return
     */
    boolean checkName(MqMessageDto mqMessageDto, boolean isAdd);

    /**
     *
     * 查询所有消息备份
     * @return
     */
    List<MqMessage> queryAll(MqMessageDto mqMessageDto);
}
