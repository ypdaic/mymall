package com.ypdaic.mymall.ware.vo;

import lombok.Data;

@Data
public class PurchaseItemDoneVo {
    //{itemId:1,status:4,reason:""}
    /**
     * 采购项id
     */
    private Long itemId;
    /**
     * 采购状态
     */
    private Integer status;

    /**
     * 原因
     */
    private String reason;
}
