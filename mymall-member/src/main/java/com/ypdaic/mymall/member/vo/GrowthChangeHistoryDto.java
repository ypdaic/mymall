package com.ypdaic.mymall.member.vo;

import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 成长值变化历史记录 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class GrowthChangeHistoryDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * member_id
	 */
	private Long memberId;

	/**
	 * create_time
	 */
	private Date createTime;

	/**
	 * 改变的值（正负计数）
	 */
	private Integer changeCount;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 积分来源[0-购物，1-管理员修改]
	 */
	private Integer sourceType;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
