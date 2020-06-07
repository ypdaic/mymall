package com.ypdaic.mymall.product.enums;

/**
 *
 * 属性分组 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsAttrGroupExcelHeadersEnum {

    ATTR_GROUP_ID("attr_group_id", "分组id"),
    ATTR_GROUP_NAME("attr_group_name", "组名"),
    SORT("sort", "排序"),
    DESCRIPT("descript", "描述"),
    ICON("icon", "组图标"),
    CATELOG_ID("catelog_id", "所属分类id");

    private String code;

    private String desc;

    PmsAttrGroupExcelHeadersEnum(String code, String desc) {
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