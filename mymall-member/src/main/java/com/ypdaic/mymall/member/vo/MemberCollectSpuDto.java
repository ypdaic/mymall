package com.ypdaic.mymall.member.vo;

import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 会员收藏的商品 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class MemberCollectSpuDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 会员id
	 */
	private Long memberId;

	/**
	 * spu_id
	 */
	private Long spuId;

	/**
	 * spu_name
	 */
	private String spuName;

	/**
	 * spu_img
	 */
	private String spuImg;

	/**
	 * create_time
	 */
	private Date createTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
