package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 首页轮播广告 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum HomeAdvExcelHeadersEnum {

    NAME("name", "名字"),
    PIC("pic", "图片地址"),
    START_TIME("start_time", "开始时间"),
    END_TIME("end_time", "结束时间"),
    STATUS("status", "状态"),
    CLICK_COUNT("click_count", "点击数"),
    URL("url", "广告详情连接地址"),
    NOTE("note", "备注"),
    SORT("sort", "排序"),
    PUBLISHER_ID("publisher_id", "发布者"),
    AUTH_ID("auth_id", "审核者");

    private String code;

    private String desc;

    HomeAdvExcelHeadersEnum(String code, String desc) {
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