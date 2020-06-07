package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * spu图片 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SpuImagesDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * spu_id
	 */
	private Long spuId;

	/**
	 * 图片名
	 */
	private String imgName;

	/**
	 * 图片地址
	 */
	private String imgUrl;

	/**
	 * 顺序
	 */
	private Integer imgSort;

	/**
	 * 是否默认图
	 */
	private Integer defaultImg;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
