package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * spu属性值 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class ProductAttrValueDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 商品id
	 */
	private Long spuId;

	/**
	 * 属性id
	 */
	private Long attrId;

	/**
	 * 属性名
	 */
	private String attrName;

	/**
	 * 属性值
	 */
	private String attrValue;

	/**
	 * 顺序
	 */
	private Integer attrSort;

	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】
	 */
	private Integer quickShow;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
