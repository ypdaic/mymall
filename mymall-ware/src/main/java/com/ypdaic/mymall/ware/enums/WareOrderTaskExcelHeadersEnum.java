package com.ypdaic.mymall.ware.enums;

/**
 *
 * 库存工作单 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum WareOrderTaskExcelHeadersEnum {

    ORDER_ID("order_id", "order_id"),
    ORDER_SN("order_sn", "order_sn"),
    CONSIGNEE("consignee", "收货人"),
    CONSIGNEE_TEL("consignee_tel", "收货人电话"),
    DELIVERY_ADDRESS("delivery_address", "配送地址"),
    ORDER_COMMENT("order_comment", "订单备注"),
    PAYMENT_WAY("payment_way", "付款方式【 1:在线付款 2:货到付款】"),
    TASK_STATUS("task_status", "任务状态"),
    ORDER_BODY("order_body", "订单描述"),
    TRACKING_NO("tracking_no", "物流单号"),
    CREATE_TIME("create_time", "create_time"),
    WARE_ID("ware_id", "仓库id"),
    TASK_COMMENT("task_comment", "工作单备注");

    private String code;

    private String desc;

    WareOrderTaskExcelHeadersEnum(String code, String desc) {
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