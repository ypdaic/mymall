package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 优惠券信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CouponExcelHeadersEnum {

    COUPON_TYPE("coupon_type", "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]"),
    COUPON_IMG("coupon_img", "优惠券图片"),
    COUPON_NAME("coupon_name", "优惠卷名字"),
    NUM("num", "数量"),
    AMOUNT("amount", "金额"),
    PER_LIMIT("per_limit", "每人限领张数"),
    MIN_POINT("min_point", "使用门槛"),
    START_TIME("start_time", "开始时间"),
    END_TIME("end_time", "结束时间"),
    USE_TYPE("use_type", "使用类型[0->全场通用；1->指定分类；2->指定商品]"),
    NOTE("note", "备注"),
    PUBLISH_COUNT("publish_count", "发行数量"),
    USE_COUNT("use_count", "已使用数量"),
    RECEIVE_COUNT("receive_count", "领取数量"),
    ENABLE_START_TIME("enable_start_time", "可以领取的开始日期"),
    ENABLE_END_TIME("enable_end_time", "可以领取的结束日期"),
    CODE("code", "优惠码"),
    MEMBER_LEVEL("member_level", "可以领取的会员等级[0->不限等级，其他-对应等级]"),
    PUBLISH("publish", "发布状态[0-未发布，1-已发布]");

    private String code;

    private String desc;

    CouponExcelHeadersEnum(String code, String desc) {
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