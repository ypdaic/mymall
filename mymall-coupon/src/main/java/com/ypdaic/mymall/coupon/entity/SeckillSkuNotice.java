package com.ypdaic.mymall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 秒杀商品通知订阅
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_seckill_sku_notice")
public class SeckillSkuNotice extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * member_id
     */
    private Long memberId;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 活动场次id
     */
    private Long sessionId;

    /**
     * 订阅时间
     */
    private Date subcribeTime;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 通知方式[0-短信，1-邮件]
     */
    private Boolean noticeType;


}
