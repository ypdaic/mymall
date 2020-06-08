package com.ypdaic.mymall.coupon.vo;

import java.math.BigDecimal;
import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 商品阶梯价格 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SkuLadderDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * spu_id
	 */
	private Long skuId;

	/**
	 * 满几件
	 */
	private Integer fullCount;

	/**
	 * 打几折
	 */
	private BigDecimal discount;

	/**
	 * 折后价
	 */
	private BigDecimal price;

	/**
	 * 是否叠加其他优惠[0-不可叠加，1-可叠加]
	 */
	private Boolean addOther;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
