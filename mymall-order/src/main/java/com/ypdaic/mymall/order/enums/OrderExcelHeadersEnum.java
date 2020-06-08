package com.ypdaic.mymall.order.enums;

/**
 *
 * 订单 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum OrderExcelHeadersEnum {

    MEMBER_ID("member_id", "member_id"),
    ORDER_SN("order_sn", "订单号"),
    COUPON_ID("coupon_id", "使用的优惠券"),
    CREATE_TIME("create_time", "create_time"),
    MEMBER_USERNAME("member_username", "用户名"),
    TOTAL_AMOUNT("total_amount", "订单总额"),
    PAY_AMOUNT("pay_amount", "应付总额"),
    FREIGHT_AMOUNT("freight_amount", "运费金额"),
    PROMOTION_AMOUNT("promotion_amount", "促销优化金额（促销价、满减、阶梯价）"),
    INTEGRATION_AMOUNT("integration_amount", "积分抵扣金额"),
    COUPON_AMOUNT("coupon_amount", "优惠券抵扣金额"),
    DISCOUNT_AMOUNT("discount_amount", "后台调整订单使用的折扣金额"),
    PAY_TYPE("pay_type", "支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】"),
    SOURCE_TYPE("source_type", "订单来源[0->PC订单；1->app订单]"),
    STATUS("status", "订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】"),
    DELIVERY_COMPANY("delivery_company", "物流公司(配送方式)"),
    DELIVERY_SN("delivery_sn", "物流单号"),
    AUTO_CONFIRM_DAY("auto_confirm_day", "自动确认时间（天）"),
    INTEGRATION("integration", "可以获得的积分"),
    GROWTH("growth", "可以获得的成长值"),
    BILL_TYPE("bill_type", "发票类型[0->不开发票；1->电子发票；2->纸质发票]"),
    BILL_HEADER("bill_header", "发票抬头"),
    BILL_CONTENT("bill_content", "发票内容"),
    BILL_RECEIVER_PHONE("bill_receiver_phone", "收票人电话"),
    BILL_RECEIVER_EMAIL("bill_receiver_email", "收票人邮箱"),
    RECEIVER_NAME("receiver_name", "收货人姓名"),
    RECEIVER_PHONE("receiver_phone", "收货人电话"),
    RECEIVER_POST_CODE("receiver_post_code", "收货人邮编"),
    RECEIVER_PROVINCE("receiver_province", "省份/直辖市"),
    RECEIVER_CITY("receiver_city", "城市"),
    RECEIVER_REGION("receiver_region", "区"),
    RECEIVER_DETAIL_ADDRESS("receiver_detail_address", "详细地址"),
    NOTE("note", "订单备注"),
    CONFIRM_STATUS("confirm_status", "确认收货状态[0->未确认；1->已确认]"),
    DELETE_STATUS("delete_status", "删除状态【0->未删除；1->已删除】"),
    USE_INTEGRATION("use_integration", "下单时使用的积分"),
    PAYMENT_TIME("payment_time", "支付时间"),
    DELIVERY_TIME("delivery_time", "发货时间"),
    RECEIVE_TIME("receive_time", "确认收货时间"),
    COMMENT_TIME("comment_time", "评价时间"),
    MODIFY_TIME("modify_time", "修改时间");

    private String code;

    private String desc;

    OrderExcelHeadersEnum(String code, String desc) {
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