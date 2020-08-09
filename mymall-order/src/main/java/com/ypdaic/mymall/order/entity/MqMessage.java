package com.ypdaic.mymall.order.entity;

import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 消息备份
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MqMessage extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * mq 消息id
     */
    private String messageId;

    /**
     * 消息体
     */
    private String content;

    /**
     * 交换器
     */
    private String toExchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 类型
     */
    private String classType;

    /**
     * 0-未消费，1-已消费
     */
    private Integer messageStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 消息类型 0：延时消息，1：非延时消息
     */
    private Integer messageType;

    public enum MessageStatus {

        NO_CONSUME(0),CONSUME(1);

        private Integer value;

        MessageStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    public enum MessageType {
        // 0: 延时 1: 非延时
        NOW(1),DELAY(0);

        private Integer value;

        MessageType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


}
