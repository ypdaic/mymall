package com.ypdaic.mymall.ware.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 商品库存 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@Data
public class WareSkuDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * 仓库id
	 */
	private Long wareId;

	/**
	 * 库存数
	 */
	private Integer stock;

	/**
	 * sku_name
	 */
	private String skuName;

	/**
	 * 锁定库存
	 */
	private Integer stockLocked;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
