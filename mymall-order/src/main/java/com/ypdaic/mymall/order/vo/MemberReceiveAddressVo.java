package com.ypdaic.mymall.order.vo;

import com.ypdaic.mymall.common.base.BaseDto;
import lombok.Data;

/**
 *
 * 会员收货地址 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class MemberReceiveAddressVo extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * member_id
	 */
	private Long memberId;

	/**
	 * 收货人姓名
	 */
	private String name;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 邮政编码
	 */
	private String postCode;

	/**
	 * 省份/直辖市
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 区
	 */
	private String region;

	/**
	 * 详细地址(街道)
	 */
	private String detailAddress;

	/**
	 * 省市区代码
	 */
	private String areacode;

	/**
	 * 是否默认
	 */
	private Boolean defaultStatus;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
