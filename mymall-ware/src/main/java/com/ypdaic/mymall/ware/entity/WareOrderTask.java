package com.ypdaic.mymall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 库存工作单
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_ware_order_task")
public class WareOrderTask extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * order_id
     */
    private Long orderId;

    /**
     * order_sn
     */
    private String orderSn;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货人电话
     */
    private String consigneeTel;

    /**
     * 配送地址
     */
    private String deliveryAddress;

    /**
     * 订单备注
     */
    private String orderComment;

    /**
     * 付款方式【 1:在线付款 2:货到付款】
     */
    private Boolean paymentWay;

    /**
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 订单描述
     */
    private String orderBody;

    /**
     * 物流单号
     */
    private String trackingNo;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 工作单备注
     */
    private String taskComment;


}
