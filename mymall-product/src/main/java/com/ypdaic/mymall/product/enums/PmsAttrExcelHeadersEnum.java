package com.ypdaic.mymall.product.enums;

/**
 *
 * 商品属性 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsAttrExcelHeadersEnum {

    ATTR_ID("attr_id", "属性id"),
    ATTR_NAME("attr_name", "属性名"),
    SEARCH_TYPE("search_type", "是否需要检索[0-不需要，1-需要]"),
    ICON("icon", "属性图标"),
    VALUE_SELECT("value_select", "可选值列表[用逗号分隔]"),
    ATTR_TYPE("attr_type", "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]"),
    ENABLE("enable", "启用状态[0 - 禁用，1 - 启用]"),
    CATELOG_ID("catelog_id", "所属分类"),
    SHOW_DESC("show_desc", "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整");

    private String code;

    private String desc;

    PmsAttrExcelHeadersEnum(String code, String desc) {
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