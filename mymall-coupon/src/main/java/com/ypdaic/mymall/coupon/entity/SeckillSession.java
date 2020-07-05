package com.ypdaic.mymall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 秒杀活动场次
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_seckill_session")
public class SeckillSession extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 场次名称
     */
    private String name;

    /**
     * 每日开始时间
     */
    private Date startTime;

    /**
     * 每日结束时间
     */
    private Date endTime;

    /**
     * 启用状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private List<SeckillSkuRelation> list;


}
