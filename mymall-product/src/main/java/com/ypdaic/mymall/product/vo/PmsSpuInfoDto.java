package com.ypdaic.mymall.product.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * spu信息 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsSpuInfoDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 商品名称
	 */
	private String spuName;

	/**
	 * 商品描述
	 */
	private String spuDescription;

	/**
	 * 所属分类id
	 */
	private Long catalogId;

	/**
	 * 品牌id
	 */
	private Long brandId;

	private BigDecimal weight;

	/**
	 * 上架状态[0 - 下架，1 - 上架]
	 */
	private Integer publishStatus;

	private Date createTime;

	private Date updateTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
