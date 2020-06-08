package com.ypdaic.mymall.coupon.vo;

import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 秒杀商品通知订阅 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SeckillSkuNoticeDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * member_id
	 */
	private Long memberId;

	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * 活动场次id
	 */
	private Long sessionId;

	/**
	 * 订阅时间
	 */
	private Date subcribeTime;

	/**
	 * 发送时间
	 */
	private Date sendTime;

	/**
	 * 通知方式[0-短信，1-邮件]
	 */
	private Boolean noticeType;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
