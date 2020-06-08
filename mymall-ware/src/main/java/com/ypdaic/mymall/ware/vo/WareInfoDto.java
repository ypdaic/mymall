package com.ypdaic.mymall.ware.vo;

import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 仓库信息 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
public class WareInfoDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 仓库名
	 */
	private String name;

	/**
	 * 仓库地址
	 */
	private String address;

	/**
	 * 区域编码
	 */
	private String areacode;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
