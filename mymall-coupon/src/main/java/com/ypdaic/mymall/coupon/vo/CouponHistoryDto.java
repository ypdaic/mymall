package com.ypdaic.mymall.coupon.vo;

import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 优惠券领取历史记录 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class CouponHistoryDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

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

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
