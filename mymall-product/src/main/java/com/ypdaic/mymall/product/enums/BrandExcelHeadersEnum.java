package com.ypdaic.mymall.product.enums;

/**
 *
 * 品牌 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum BrandExcelHeadersEnum {

    BRAND_ID("brand_id", "品牌id"),
    NAME("name", "品牌名"),
    LOGO("logo", "品牌logo地址"),
    DESCRIPT("descript", "介绍"),
    SHOW_STATUS("show_status", "显示状态[0-不显示；1-显示]"),
    FIRST_LETTER("first_letter", "检索首字母"),
    SORT("sort", "排序");

    private String code;

    private String desc;

    BrandExcelHeadersEnum(String code, String desc) {
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