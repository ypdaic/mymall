package com.ypdaic.mymall.ware.enums;

/**
 *
 * 采购信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum PurchaseExcelHeadersEnum {

    ASSIGNEE_ID("assignee_id", "采购人id"),
    ASSIGNEE_NAME("assignee_name", "采购人名"),
    PHONE("phone", "联系方式"),
    PRIORITY("priority", "优先级"),
    STATUS("status", "状态"),
    WARE_ID("ware_id", "仓库id"),
    AMOUNT("amount", "总金额"),
    CREATE_TIME("create_time", "创建日期"),
    UPDATE_TIME("update_time", "更新日期");

    private String code;

    private String desc;

    PurchaseExcelHeadersEnum(String code, String desc) {
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