package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 优惠券领取历史记录 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CouponHistoryExcelHeadersEnum {

    COUPON_ID("coupon_id", "优惠券id"),
    MEMBER_ID("member_id", "会员id"),
    MEMBER_NICK_NAME("member_nick_name", "会员名字"),
    GET_TYPE("get_type", "获取方式[0->后台赠送；1->主动领取]"),
    CREATE_TIME("create_time", "创建时间"),
    USE_TYPE("use_type", "使用状态[0->未使用；1->已使用；2->已过期]"),
    USE_TIME("use_time", "使用时间"),
    ORDER_ID("order_id", "订单id"),
    ORDER_SN("order_sn", "订单号");

    private String code;

    private String desc;

    CouponHistoryExcelHeadersEnum(String code, String desc) {
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