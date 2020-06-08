package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 优惠券分类关联 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CouponSpuCategoryRelationExcelHeadersEnum {

    COUPON_ID("coupon_id", "优惠券id"),
    CATEGORY_ID("category_id", "产品分类id"),
    CATEGORY_NAME("category_name", "产品分类名称");

    private String code;

    private String desc;

    CouponSpuCategoryRelationExcelHeadersEnum(String code, String desc) {
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