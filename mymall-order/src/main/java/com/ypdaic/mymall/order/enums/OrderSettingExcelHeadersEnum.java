package com.ypdaic.mymall.order.enums;

/**
 *
 * 订单配置信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum OrderSettingExcelHeadersEnum {

    FLASH_ORDER_OVERTIME("flash_order_overtime", "秒杀订单超时关闭时间(分)"),
    NORMAL_ORDER_OVERTIME("normal_order_overtime", "正常订单超时时间(分)"),
    CONFIRM_OVERTIME("confirm_overtime", "发货后自动确认收货时间（天）"),
    FINISH_OVERTIME("finish_overtime", "自动完成交易时间，不能申请退货（天）"),
    COMMENT_OVERTIME("comment_overtime", "订单完成后自动好评时间（天）"),
    MEMBER_LEVEL("member_level", "会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】");

    private String code;

    private String desc;

    OrderSettingExcelHeadersEnum(String code, String desc) {
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