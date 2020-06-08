package com.ypdaic.mymall.coupon.vo;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 秒杀活动商品关联 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SeckillSkuRelationDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 活动id
	 */
	private Long promotionId;

	/**
	 * 活动场次id
	 */
	private Long promotionSessionId;

	/**
	 * 商品id
	 */
	private Long skuId;

	/**
	 * 秒杀价格
	 */
	private BigDecimal seckillPrice;

	/**
	 * 秒杀总量
	 */
	private BigDecimal seckillCount;

	/**
	 * 每人限购数量
	 */
	private BigDecimal seckillLimit;

	/**
	 * 排序
	 */
	private Integer seckillSort;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
