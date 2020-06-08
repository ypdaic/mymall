package com.ypdaic.mymall.member.vo;

import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 会员登录记录 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class MemberLoginLogDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * member_id
	 */
	private Long memberId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * city
	 */
	private String city;

	/**
	 * 登录类型[1-web，2-app]
	 */
	private Boolean loginType;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
