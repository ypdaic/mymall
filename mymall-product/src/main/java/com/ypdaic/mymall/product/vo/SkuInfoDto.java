package com.ypdaic.mymall.product.vo;

import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * sku信息 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SkuInfoDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * skuId
	 */
	private Long skuId;

	/**
	 * spuId
	 */
	private Long spuId;

	/**
	 * sku名称
	 */
	private String skuName;

	/**
	 * sku介绍描述
	 */
	private String skuDesc;

	/**
	 * 所属分类id
	 */
	private Long catalogId;

	/**
	 * 品牌id
	 */
	private Long brandId;

	/**
	 * 默认图片
	 */
	private String skuDefaultImg;

	/**
	 * 标题
	 */
	private String skuTitle;

	/**
	 * 副标题
	 */
	private String skuSubtitle;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 销量
	 */
	private Long saleCount;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
