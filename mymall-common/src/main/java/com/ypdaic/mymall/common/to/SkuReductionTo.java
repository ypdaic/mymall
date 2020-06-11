package com.ypdaic.mymall.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品优惠信息
 */
@Data
public class SkuReductionTo {

    private Long skuId;
    // 满几件
    private int fullCount;
    // 打几折
    private BigDecimal discount;
    private int countStatus;
    // 满多少
    private BigDecimal fullPrice;
    // 减多少
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
