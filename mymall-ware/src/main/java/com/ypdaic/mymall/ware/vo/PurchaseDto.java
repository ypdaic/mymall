package com.ypdaic.mymall.ware.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 采购信息 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class PurchaseDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 采购人id
	 */
	private Long assigneeId;

	/**
	 * 采购人名
	 */
	private String assigneeName;

	/**
	 * 联系方式
	 */
	private String phone;

	/**
	 * 优先级
	 */
	private Integer priority;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 仓库id
	 */
	private Long wareId;

	/**
	 * 总金额
	 */
	private BigDecimal amount;

	/**
	 * 创建日期
	 */
	private Date createTime;

	/**
	 * 更新日期
	 */
	private Date updateTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
