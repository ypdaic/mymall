package com.ypdaic.mymall.order.vo;

import com.ypdaic.mymall.order.entity.Order;
import lombok.Data;

/**
 * 订单下单成功返回
 */
@Data
public class SubmitResponVo {

    private Order order;

    private Integer code;
}
