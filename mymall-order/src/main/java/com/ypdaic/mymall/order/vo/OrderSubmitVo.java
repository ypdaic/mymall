package com.ypdaic.mymall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderSubmitVo {

    // 收货地址id
    private Long addrId;

    //付款方式
    private Integer payType;

    // 无需提交需要购买的商品，去购物车再取一遍

    // 防重令牌
    private String orderToken;

    // 应付价格
    private BigDecimal payPrice;

    // 用户信息，直接去session中取

    // 订单备注
    private String node;



}
