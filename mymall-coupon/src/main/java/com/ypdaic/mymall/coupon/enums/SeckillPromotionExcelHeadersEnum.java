package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 秒杀活动 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SeckillPromotionExcelHeadersEnum {

    TITLE("title", "活动标题"),
    START_TIME("start_time", "开始日期"),
    END_TIME("end_time", "结束日期"),
    STATUS("status", "上下线状态"),
    CREATE_TIME("create_time", "创建时间"),
    USER_ID("user_id", "创建人");

    private String code;

    private String desc;

    SeckillPromotionExcelHeadersEnum(String code, String desc) {
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