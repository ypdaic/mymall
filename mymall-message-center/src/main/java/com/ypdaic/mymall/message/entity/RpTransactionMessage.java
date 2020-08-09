package com.ypdaic.mymall.message.entity;

import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 *
 * 
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RpTransactionMessage extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 修改者
     */
    private String editor;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 最后修改时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 消息内容
     */
    private String messageBody;

    /**
     * 消息数据类型
     */
    private String messageDataType;

    /**
     * 消费队列
     */
//    @NotEmpty(message = )
    private String consumerQueue;

    /**
     * 交换器
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 消息重发次数
     */
    private Integer messageSendTimes;

    /**
     * 是否死亡
     */
    private String areadlyDead;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段1
     */
    private String field1;

    /**
     * 扩展字段2
     */
    private String field2;

    /**
     * 扩展字段3
     */
    private String field3;

    /**
     * 回查url
     */
    private String url;

    public void addSendTimes() {
        messageSendTimes++;
    }
}
