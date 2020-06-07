package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * sku销售属性&值 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsSkuSaleAttrValueDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * attr_id
	 */
	private Long attrId;

	/**
	 * 销售属性名
	 */
	private String attrName;

	/**
	 * 销售属性值
	 */
	private String attrValue;

	/**
	 * 顺序
	 */
	private Integer attrSort;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
