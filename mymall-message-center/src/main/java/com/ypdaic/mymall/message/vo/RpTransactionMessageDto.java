package com.ypdaic.mymall.message.vo;

import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 *  DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@Data
public class RpTransactionMessageDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * 修改者
	 */
	private String editor;

	/**
	 * 创建者
	 */
	private String creater;

	/**
	 * 最后修改时间
	 */
	private Date editTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 消息ID
	 */
	private String messageId;

	/**
	 * 消息内容
	 */
	private String messageBody;

	/**
	 * 消息数据类型
	 */
	private String messageDataType;

	/**
	 * 消费队列
	 */
	private String consumerQueue;

	/**
	 * 消息重发次数
	 */
	private Integer messageSendTimes;

	/**
	 * 是否死亡
	 */
	private String areadlyDead;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 扩展字段1
	 */
	private String field1;

	/**
	 * 扩展字段2
	 */
	private String field2;

	/**
	 * 扩展字段3
	 */
	private String field3;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
