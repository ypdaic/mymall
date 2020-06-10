package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * spu信息介绍 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-10
 */
@Data
public class SpuInfoDescDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 商品id
	 */
	private Long spuId;

	/**
	 * 商品介绍
	 */
	private String decript;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
