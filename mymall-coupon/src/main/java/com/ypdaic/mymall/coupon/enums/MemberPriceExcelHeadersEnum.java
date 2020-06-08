package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 商品会员价格 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberPriceExcelHeadersEnum {

    SKU_ID("sku_id", "sku_id"),
    MEMBER_LEVEL_ID("member_level_id", "会员等级id"),
    MEMBER_LEVEL_NAME("member_level_name", "会员等级名"),
    MEMBER_PRICE("member_price", "会员对应价格"),
    ADD_OTHER("add_other", "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]");

    private String code;

    private String desc;

    MemberPriceExcelHeadersEnum(String code, String desc) {
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