package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 属性&属性分组关联 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsAttrAttrgroupRelationDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 属性id
	 */
	private Long attrId;

	/**
	 * 属性分组id
	 */
	private Long attrGroupId;

	/**
	 * 属性组内排序
	 */
	private Integer attrSort;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
