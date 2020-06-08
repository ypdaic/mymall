package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 秒杀活动场次 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SeckillSessionExcelHeadersEnum {

    NAME("name", "场次名称"),
    START_TIME("start_time", "每日开始时间"),
    END_TIME("end_time", "每日结束时间"),
    STATUS("status", "启用状态"),
    CREATE_TIME("create_time", "创建时间");

    private String code;

    private String desc;

    SeckillSessionExcelHeadersEnum(String code, String desc) {
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