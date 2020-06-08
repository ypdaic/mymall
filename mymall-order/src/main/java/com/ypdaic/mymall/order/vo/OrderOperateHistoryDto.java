package com.ypdaic.mymall.order.vo;

import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 订单操作历史记录 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class OrderOperateHistoryDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 订单id
	 */
	private Long orderId;

	/**
	 * 操作人[用户；系统；后台管理员]
	 */
	private String operateMan;

	/**
	 * 操作时间
	 */
	private Date createTime;

	/**
	 * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
	 */
	private Integer orderStatus;

	/**
	 * 备注
	 */
	private String note;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
