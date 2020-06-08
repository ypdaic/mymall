package com.ypdaic.mymall.order.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 支付信息表 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class PaymentInfoDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 订单号（对外业务号）
	 */
	private String orderSn;

	/**
	 * 订单id
	 */
	private Long orderId;

	/**
	 * 支付宝交易流水号
	 */
	private String alipayTradeNo;

	/**
	 * 支付总金额
	 */
	private BigDecimal totalAmount;

	/**
	 * 交易内容
	 */
	private String subject;

	/**
	 * 支付状态
	 */
	private String paymentStatus;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 确认时间
	 */
	private Date confirmTime;

	/**
	 * 回调内容
	 */
	private String callbackContent;

	/**
	 * 回调时间
	 */
	private Date callbackTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
