package com.ypdaic.mymall.ware.enums;

/**
 *
 * 商品库存 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-11
 */
public enum WareSkuExcelHeadersEnum {

    SKU_ID("sku_id", "sku_id"),
    WARE_ID("ware_id", "仓库id"),
    STOCK("stock", "库存数"),
    SKU_NAME("sku_name", "sku_name"),
    STOCK_LOCKED("stock_locked", "锁定库存");

    private String code;

    private String desc;

    WareSkuExcelHeadersEnum(String code, String desc) {
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