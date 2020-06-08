package com.ypdaic.mymall.ware.enums;

/**
 *
 * 库存工作单 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum WareOrderTaskDetailExcelHeadersEnum {

    SKU_ID("sku_id", "sku_id"),
    SKU_NAME("sku_name", "sku_name"),
    SKU_NUM("sku_num", "购买个数"),
    TASK_ID("task_id", "工作单id");

    private String code;

    private String desc;

    WareOrderTaskDetailExcelHeadersEnum(String code, String desc) {
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