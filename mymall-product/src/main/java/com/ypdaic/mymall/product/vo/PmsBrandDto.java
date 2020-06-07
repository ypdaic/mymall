package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 品牌 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsBrandDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 品牌id
	 */
	private Long brandId;

	/**
	 * 品牌名
	 */
	private String name;

	/**
	 * 品牌logo地址
	 */
	private String logo;

	/**
	 * 介绍
	 */
	private String descript;

	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	private Integer showStatus;

	/**
	 * 检索首字母
	 */
	private String firstLetter;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
