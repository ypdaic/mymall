package com.ypdaic.mymall.ware.vo;

import java.util.Date;
import java.util.Date;
import lombok.Data;
import com.ypdaic.mymall.common.base.BaseDto;

/**
 *
 * 消息备份 DTO对象
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@Data
public class MqMessageDto extends BaseDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * mq 消息id
	 */
	private String messageId;

	/**
	 * mq 消息类型，0: 延时消息 1: 非延时消息
	 */
	private Integer messageType;

	/**
	 * 消息体
	 */
	private String content;

	/**
	 * 交换器
	 */
	private String toExchange;

	/**
	 * 路由键
	 */
	private String routingKey;

	/**
	 * 类型
	 */
	private String classType;

	/**
	 * 0-新建 1-已发送 2-错误抵达 3-已抵达
	 */
	private Integer messageStatus;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
     * 是否新增
     */
	private Boolean isAdd;

}
