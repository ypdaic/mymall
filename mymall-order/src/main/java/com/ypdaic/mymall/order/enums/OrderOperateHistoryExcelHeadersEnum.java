package com.ypdaic.mymall.order.enums;

/**
 *
 * 订单操作历史记录 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum OrderOperateHistoryExcelHeadersEnum {

    ORDER_ID("order_id", "订单id"),
    OPERATE_MAN("operate_man", "操作人[用户；系统；后台管理员]"),
    CREATE_TIME("create_time", "操作时间"),
    ORDER_STATUS("order_status", "订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】"),
    NOTE("note", "备注");

    private String code;

    private String desc;

    OrderOperateHistoryExcelHeadersEnum(String code, String desc) {
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