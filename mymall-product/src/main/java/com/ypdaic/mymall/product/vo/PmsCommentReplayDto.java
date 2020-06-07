package com.ypdaic.mymall.product.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 商品评价回复关系 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
public class PmsCommentReplayDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 评论id
	 */
	private Long commentId;

	/**
	 * 回复id
	 */
	private Long replyId;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
