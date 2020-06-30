package com.ypdaic.mymall.common.to;

import lombok.Data;

import java.util.List;

@Data
public class WareSkuLockVo {

    // 订单号
    private String orderSn;

    // 需要锁库存的信息
    private List<OrderItemVo> locks;
}
