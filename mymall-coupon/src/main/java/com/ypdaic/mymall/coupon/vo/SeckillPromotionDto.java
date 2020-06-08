package com.ypdaic.mymall.coupon.vo;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 秒杀活动 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SeckillPromotionDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 活动标题
	 */
	private String title;

	/**
	 * 开始日期
	 */
	private Date startTime;

	/**
	 * 结束日期
	 */
	private Date endTime;

	/**
	 * 上下线状态
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private Long userId;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
