package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 专题商品 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum HomeSubjectSpuExcelHeadersEnum {

    NAME("name", "专题名字"),
    SUBJECT_ID("subject_id", "专题id"),
    SPU_ID("spu_id", "spu_id"),
    SORT("sort", "排序");

    private String code;

    private String desc;

    HomeSubjectSpuExcelHeadersEnum(String code, String desc) {
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