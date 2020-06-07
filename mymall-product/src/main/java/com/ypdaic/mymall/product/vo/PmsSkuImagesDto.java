package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * sku图片 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsSkuImagesDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * 图片地址
	 */
	private String imgUrl;

	/**
	 * 排序
	 */
	private Integer imgSort;

	/**
	 * 默认图[0 - 不是默认图，1 - 是默认图]
	 */
	private Integer defaultImg;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
