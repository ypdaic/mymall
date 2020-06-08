package com.ypdaic.mymall.coupon.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 优惠券与产品关联 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class CouponSpuRelationDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 优惠券id
	 */
	private Long couponId;

	/**
	 * spu_id
	 */
	private Long spuId;

	/**
	 * spu_name
	 */
	private String spuName;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
