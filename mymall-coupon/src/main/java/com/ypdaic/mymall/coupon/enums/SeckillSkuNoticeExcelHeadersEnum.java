package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 秒杀商品通知订阅 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SeckillSkuNoticeExcelHeadersEnum {

    MEMBER_ID("member_id", "member_id"),
    SKU_ID("sku_id", "sku_id"),
    SESSION_ID("session_id", "活动场次id"),
    SUBCRIBE_TIME("subcribe_time", "订阅时间"),
    SEND_TIME("send_time", "发送时间"),
    NOTICE_TYPE("notice_type", "通知方式[0-短信，1-邮件]");

    private String code;

    private String desc;

    SeckillSkuNoticeExcelHeadersEnum(String code, String desc) {
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