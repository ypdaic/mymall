package com.ypdaic.mymall.ware.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 库存工作单 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class WareOrderTaskDetailDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * sku_name
	 */
	private String skuName;

	/**
	 * 购买个数
	 */
	private Integer skuNum;

	/**
	 * 工作单id
	 */
	private Long taskId;

	/**
     * 是否新增
     */
	private Boolean isAdd;

	/**
	 * 库存id
	 */
	private Long wareId;

	/**
	 * 锁定状态 1-锁定 2-解锁 3-扣减
	 */
	private Integer lockStatus;

}
