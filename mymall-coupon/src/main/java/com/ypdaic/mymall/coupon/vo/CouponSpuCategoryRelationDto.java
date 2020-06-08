package com.ypdaic.mymall.coupon.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 优惠券分类关联 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class CouponSpuCategoryRelationDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 优惠券id
	 */
	private Long couponId;

	/**
	 * 产品分类id
	 */
	private Long categoryId;

	/**
	 * 产品分类名称
	 */
	private String categoryName;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
