package com.ypdaic.mymall.coupon.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 商品spu积分设置
 *
 *
 * @author daiyanping
 * @since 2020-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_spu_bounds")
public class SpuBounds extends SuperEntity {

    private static final long serialVersionUID = 1L;

    private Long spuId;

    /**
     * 成长积分
     */
    private BigDecimal growBounds;

    /**
     * 购物积分
     */
    private BigDecimal buyBounds;

    /**
     * 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
     */
    private Boolean work;


}
