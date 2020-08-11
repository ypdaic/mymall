package com.ypdaic.mymall.ware.vo;

import lombok.Data;

/**
 * 锁定结果
 */
@Data
public class LockStockResult {

    // skuid
    private Long skuId;

    // 锁定数量
    private Integer num;

    // 是否已经被锁定
    private boolean locked;
}
