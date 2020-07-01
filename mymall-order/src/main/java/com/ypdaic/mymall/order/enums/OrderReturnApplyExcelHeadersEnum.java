package com.ypdaic.mymall.order.enums;

/**
 *
 * 订单退货申请 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum OrderReturnApplyExcelHeadersEnum {

    ORDER_ID("order_id", "order_id"),
    SKU_ID("sku_id", "退货商品id"),
    ORDER_SN("order_sn", "订单编号"),
    CREATE_TIME("create_time", "申请时间"),
    MEMBER_USERNAME("member_username", "会员用户名"),
    RETURN_AMOUNT("return_amount", "退款金额"),
    RETURN_NAME("return_name", "退货人姓名"),
    RETURN_PHONE("return_phone", "退货人电话"),
    STATUS("status", "申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]"),
    HANDLE_TIME("handle_time", "处理时间"),
    SKU_IMG("sku_img", "商品图片"),
    SKU_NAME("sku_name", "商品名称"),
    SKU_BRAND("sku_brand", "商品品牌"),
    SKU_ATTRS_VALS("sku_attrs_vals", "商品销售属性(JSON)"),
    SKU_COUNT("sku_count", "退货数量"),
    SKU_PRICE("sku_price", "商品单价"),
    SKU_REAL_PRICE("sku_real_price", "商品实际支付单价"),
    REASON("reason", "原因"),
    DESCRIPTION述("description述", "描述"),
    DESC_PICS("desc_pics", "凭证图片，以逗号隔开"),
    HANDLE_NOTE("handle_note", "处理备注"),
    HANDLE_MAN("handle_man", "处理人员"),
    RECEIVE_MAN("receive_man", "收货人"),
    RECEIVE_TIME("receive_time", "收货时间"),
    RECEIVE_NOTE("receive_note", "收货备注"),
    RECEIVE_PHONE("receive_phone", "收货电话"),
    COMPANY_ADDRESS("company_address", "公司收货地址");

    private String code;

    private String desc;

    OrderReturnApplyExcelHeadersEnum(String code, String desc) {
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