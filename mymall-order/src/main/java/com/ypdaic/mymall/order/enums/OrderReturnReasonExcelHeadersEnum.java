package com.ypdaic.mymall.order.enums;

/**
 *
 * 退货原因 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum OrderReturnReasonExcelHeadersEnum {

    NAME("name", "退货原因名"),
    SORT("sort", "排序"),
    STATUS("status", "启用状态"),
    CREATE_TIME("create_time", "create_time");

    private String code;

    private String desc;

    OrderReturnReasonExcelHeadersEnum(String code, String desc) {
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