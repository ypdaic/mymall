package com.ypdaic.mymall.coupon.vo;

import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 商品会员价格 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class MemberPriceDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * 会员等级id
	 */
	private Long memberLevelId;

	/**
	 * 会员等级名
	 */
	private String memberLevelName;

	/**
	 * 会员对应价格
	 */
	private BigDecimal memberPrice;

	/**
	 * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
	 */
	private Integer addOther;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
