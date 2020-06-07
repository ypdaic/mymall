package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 品牌分类关联 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class CategoryBrandRelationDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 品牌id
	 */
	private Long brandId;

	/**
	 * 分类id
	 */
	private Long catelogId;

	private String brandName;

	private String catelogName;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
