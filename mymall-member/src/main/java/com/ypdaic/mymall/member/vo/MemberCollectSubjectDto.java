package com.ypdaic.mymall.member.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 会员收藏的专题活动 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class MemberCollectSubjectDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * subject_id
	 */
	private Long subjectId;

	/**
	 * subject_name
	 */
	private String subjectName;

	/**
	 * subject_img
	 */
	private String subjectImg;

	/**
	 * 活动url
	 */
	private String subjectUrll;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
