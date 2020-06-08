package com.ypdaic.mymall.order.vo;

import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 退款信息 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class RefundInfoDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 退款的订单
	 */
	private Long orderReturnId;

	/**
	 * 退款金额
	 */
	private BigDecimal refund;

	/**
	 * 退款交易流水号
	 */
	private String refundSn;

	/**
	 * 退款状态
	 */
	private Boolean refundStatus;

	/**
	 * 退款渠道[1-支付宝，2-微信，3-银联，4-汇款]
	 */
	private Integer refundChannel;

	private String refundContent;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
