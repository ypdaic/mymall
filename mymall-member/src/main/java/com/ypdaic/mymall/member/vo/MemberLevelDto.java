package com.ypdaic.mymall.member.vo;

import java.math.BigDecimal;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 会员等级 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class MemberLevelDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 等级名称
	 */
	private String name;

	/**
	 * 等级需要的成长值
	 */
	private Integer growthPoint;

	/**
	 * 是否为默认等级[0->不是；1->是]
	 */
	private Integer defaultStatus;

	/**
	 * 免运费标准
	 */
	private BigDecimal freeFreightPoint;

	/**
	 * 每次评价获取的成长值
	 */
	private Integer commentGrowthPoint;

	/**
	 * 是否有免邮特权
	 */
	private Integer priviledgeFreeFreight;

	/**
	 * 是否有会员价格特权
	 */
	private Integer priviledgeMemberPrice;

	/**
	 * 是否有生日特权
	 */
	private Integer priviledgeBirthday;

	/**
	 * 备注
	 */
	private String note;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
