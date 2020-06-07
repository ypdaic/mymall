package com.ypdaic.mymall.product.enums;

/**
 *
 * 品牌分类关联 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CategoryBrandRelationExcelHeadersEnum {

    BRAND_ID("brand_id", "品牌id"),
    CATELOG_ID("catelog_id", "分类id"),
    BRAND_NAME("brand_name", ""),
    CATELOG_NAME("catelog_name", "");

    private String code;

    private String desc;

    CategoryBrandRelationExcelHeadersEnum(String code, String desc) {
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