package com.ypdaic.mymall.order.vo;

import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 退货原因 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class OrderReturnReasonDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 退货原因名
	 */
	private String name;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 启用状态
	 */
	private Boolean status;

	/**
	 * create_time
	 */
	private Date createTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
