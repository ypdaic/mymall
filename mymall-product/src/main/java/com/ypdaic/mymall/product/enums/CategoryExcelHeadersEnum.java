package com.ypdaic.mymall.product.enums;

/**
 *
 * 商品三级分类 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CategoryExcelHeadersEnum {

    CAT_ID("cat_id", "分类id"),
    NAME("name", "分类名称"),
    PARENT_CID("parent_cid", "父分类id"),
    CAT_LEVEL("cat_level", "层级"),
    SHOW_STATUS("show_status", "是否显示[0-不显示，1显示]"),
    SORT("sort", "排序"),
    ICON("icon", "图标地址"),
    PRODUCT_UNIT("product_unit", "计量单位"),
    PRODUCT_COUNT("product_count", "商品数量");

    private String code;

    private String desc;

    CategoryExcelHeadersEnum(String code, String desc) {
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