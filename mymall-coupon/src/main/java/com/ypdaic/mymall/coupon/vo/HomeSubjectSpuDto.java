package com.ypdaic.mymall.coupon.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 专题商品 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class HomeSubjectSpuDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 专题名字
	 */
	private String name;

	/**
	 * 专题id
	 */
	private Long subjectId;

	/**
	 * spu_id
	 */
	private Long spuId;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
