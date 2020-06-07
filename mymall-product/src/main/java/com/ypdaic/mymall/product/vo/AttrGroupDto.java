package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 属性分组 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class AttrGroupDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 分组id
	 */
	private Long attrGroupId;

	/**
	 * 组名
	 */
	private String attrGroupName;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 描述
	 */
	private String descript;

	/**
	 * 组图标
	 */
	private String icon;

	/**
	 * 所属分类id
	 */
	private Long catelogId;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
