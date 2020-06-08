package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员登录记录 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberLoginLogExcelHeadersEnum {

    MEMBER_ID("member_id", "member_id"),
    CREATE_TIME("create_time", "创建时间"),
    IP("ip", "ip"),
    CITY("city", "city"),
    LOGIN_TYPE("login_type", "登录类型[1-web，2-app]");

    private String code;

    private String desc;

    MemberLoginLogExcelHeadersEnum(String code, String desc) {
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