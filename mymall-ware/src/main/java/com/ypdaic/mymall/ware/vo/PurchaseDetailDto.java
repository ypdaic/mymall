package com.ypdaic.mymall.ware.vo;

import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 *  DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class PurchaseDetailDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 采购单id
	 */
	private Long purchaseId;

	/**
	 * 采购商品id
	 */
	private Long skuId;

	/**
	 * 采购数量
	 */
	private Integer skuNum;

	/**
	 * 采购金额
	 */
	private BigDecimal skuPrice;

	/**
	 * 仓库id
	 */
	private Long wareId;

	/**
	 * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
	 */
	private Integer status;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
