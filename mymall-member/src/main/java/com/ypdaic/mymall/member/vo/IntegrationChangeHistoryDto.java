package com.ypdaic.mymall.member.vo;

import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 积分变化历史记录 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class IntegrationChangeHistoryDto extends BaseDto {

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
	 * 变化的值
	 */
	private Integer changeCount;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 来源[0->购物；1->管理员修改;2->活动]
	 */
	private Integer sourceTyoe;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
