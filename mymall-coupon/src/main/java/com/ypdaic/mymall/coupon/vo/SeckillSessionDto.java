package com.ypdaic.mymall.coupon.vo;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 秒杀活动场次 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class SeckillSessionDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 场次名称
	 */
	private String name;

	/**
	 * 每日开始时间
	 */
	private Date startTime;

	/**
	 * 每日结束时间
	 */
	private Date endTime;

	/**
	 * 启用状态
	 */
	private Boolean status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
