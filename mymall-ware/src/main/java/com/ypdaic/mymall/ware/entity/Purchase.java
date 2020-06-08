package com.ypdaic.mymall.ware.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 采购信息
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_purchase")
public class Purchase extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 采购人id
     */
    private Long assigneeId;

    /**
     * 采购人名
     */
    private String assigneeName;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 总金额
     */
    private BigDecimal amount;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;


}
