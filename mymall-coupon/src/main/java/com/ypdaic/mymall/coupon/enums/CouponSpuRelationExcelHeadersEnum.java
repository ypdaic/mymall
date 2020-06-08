package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 优惠券与产品关联 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CouponSpuRelationExcelHeadersEnum {

    COUPON_ID("coupon_id", "优惠券id"),
    SPU_ID("spu_id", "spu_id"),
    SPU_NAME("spu_name", "spu_name");

    private String code;

    private String desc;

    CouponSpuRelationExcelHeadersEnum(String code, String desc) {
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