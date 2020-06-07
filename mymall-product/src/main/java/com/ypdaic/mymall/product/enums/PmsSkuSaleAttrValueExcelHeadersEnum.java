package com.ypdaic.mymall.product.enums;

/**
 *
 * sku销售属性&值 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsSkuSaleAttrValueExcelHeadersEnum {

    SKU_ID("sku_id", "sku_id"),
    ATTR_ID("attr_id", "attr_id"),
    ATTR_NAME("attr_name", "销售属性名"),
    ATTR_VALUE("attr_value", "销售属性值"),
    ATTR_SORT("attr_sort", "顺序");

    private String code;

    private String desc;

    PmsSkuSaleAttrValueExcelHeadersEnum(String code, String desc) {
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