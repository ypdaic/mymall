package com.ypdaic.mymall.coupon.vo;

import java.math.BigDecimal;
import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 商品满减信息 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SkuFullReductionDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * spu_id
	 */
	private Long skuId;

	/**
	 * 满多少
	 */
	private BigDecimal fullPrice;

	/**
	 * 减多少
	 */
	private BigDecimal reducePrice;

	/**
	 * 是否参与其他优惠
	 */
	private Boolean addOther;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
