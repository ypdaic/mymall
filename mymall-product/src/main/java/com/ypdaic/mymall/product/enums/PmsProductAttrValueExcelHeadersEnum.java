package com.ypdaic.mymall.product.enums;

/**
 *
 * spu属性值 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsProductAttrValueExcelHeadersEnum {

    SPU_ID("spu_id", "商品id"),
    ATTR_ID("attr_id", "属性id"),
    ATTR_NAME("attr_name", "属性名"),
    ATTR_VALUE("attr_value", "属性值"),
    ATTR_SORT("attr_sort", "顺序"),
    QUICK_SHOW("quick_show", "快速展示【是否展示在介绍上；0-否 1-是】");

    private String code;

    private String desc;

    PmsProductAttrValueExcelHeadersEnum(String code, String desc) {
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