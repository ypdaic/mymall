package com.ypdaic.mymall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 优惠券领取历史记录
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_coupon_history")
public class CouponHistory extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员名字
     */
    private String memberNickName;

    /**
     * 获取方式[0->后台赠送；1->主动领取]
     */
    private Boolean getType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 使用状态[0->未使用；1->已使用；2->已过期]
     */
    private Boolean useType;

    /**
     * 使用时间
     */
    private Date useTime;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private Long orderSn;


}
