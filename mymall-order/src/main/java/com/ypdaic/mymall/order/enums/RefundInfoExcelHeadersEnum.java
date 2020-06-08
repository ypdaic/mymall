package com.ypdaic.mymall.order.enums;

/**
 *
 * 退款信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum RefundInfoExcelHeadersEnum {

    ORDER_RETURN_ID("order_return_id", "退款的订单"),
    REFUND("refund", "退款金额"),
    REFUND_SN("refund_sn", "退款交易流水号"),
    REFUND_STATUS("refund_status", "退款状态"),
    REFUND_CHANNEL("refund_channel", "退款渠道[1-支付宝，2-微信，3-银联，4-汇款]"),
    REFUND_CONTENT("refund_content", "");

    private String code;

    private String desc;

    RefundInfoExcelHeadersEnum(String code, String desc) {
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