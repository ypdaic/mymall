package com.ypdaic.mymall.ware.enums;

/**
 *
 *  导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum PurchaseDetailExcelHeadersEnum {

    PURCHASE_ID("purchase_id", "采购单id"),
    SKU_ID("sku_id", "采购商品id"),
    SKU_NUM("sku_num", "采购数量"),
    SKU_PRICE("sku_price", "采购金额"),
    WARE_ID("ware_id", "仓库id"),
    STATUS("status", "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]");

    private String code;

    private String desc;

    PurchaseDetailExcelHeadersEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}