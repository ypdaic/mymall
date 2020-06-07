package com.ypdaic.mymall.product.enums;

/**
 *
 * 属性&属性分组关联 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum AttrAttrgroupRelationExcelHeadersEnum {

    ATTR_ID("attr_id", "属性id"),
    ATTR_GROUP_ID("attr_group_id", "属性分组id"),
    ATTR_SORT("attr_sort", "属性组内排序");

    private String code;

    private String desc;

    AttrAttrgroupRelationExcelHeadersEnum(String code, String desc) {
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