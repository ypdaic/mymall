package com.ypdaic.mymall.order.vo;

import com.ypdaic.mymall.order.entity.Order;
import com.ypdaic.mymall.order.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateTo {

    private Order order;

    // 订单项
    private List<OrderItem> orderItemList;

    // 计算应付价格
    private BigDecimal payPrice;

    // 运费
    private BigDecimal fare;
}
