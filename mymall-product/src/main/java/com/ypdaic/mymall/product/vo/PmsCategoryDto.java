package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 商品三级分类 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsCategoryDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 分类id
	 */
	private Long catId;

	/**
	 * 分类名称
	 */
	private String name;

	/**
	 * 父分类id
	 */
	private Long parentCid;

	/**
	 * 层级
	 */
	private Integer catLevel;

	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Integer showStatus;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 图标地址
	 */
	private String icon;

	/**
	 * 计量单位
	 */
	private String productUnit;

	/**
	 * 商品数量
	 */
	private Integer productCount;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
