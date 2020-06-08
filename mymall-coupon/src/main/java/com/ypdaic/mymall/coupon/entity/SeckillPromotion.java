package com.ypdaic.mymall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 秒杀活动
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_seckill_promotion")
public class SeckillPromotion extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 开始日期
     */
    private Date startTime;

    /**
     * 结束日期
     */
    private Date endTime;

    /**
     * 上下线状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long userId;


}
