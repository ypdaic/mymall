package com.ypdaic.mymall.order.enums;

/**
 *
 * 支付信息表 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum PaymentInfoExcelHeadersEnum {

    ORDER_SN("order_sn", "订单号（对外业务号）"),
    ORDER_ID("order_id", "订单id"),
    ALIPAY_TRADE_NO("alipay_trade_no", "支付宝交易流水号"),
    TOTAL_AMOUNT("total_amount", "支付总金额"),
    SUBJECT("subject", "交易内容"),
    PAYMENT_STATUS("payment_status", "支付状态"),
    CREATE_TIME("create_time", "创建时间"),
    CONFIRM_TIME("confirm_time", "确认时间"),
    CALLBACK_CONTENT("callback_content", "回调内容"),
    CALLBACK_TIME("callback_time", "回调时间");

    private String code;

    private String desc;

    PaymentInfoExcelHeadersEnum(String code, String desc) {
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