package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberExcelHeadersEnum {

    LEVEL_ID("level_id", "会员等级id"),
    USERNAME("username", "用户名"),
    PASSWORD("password", "密码"),
    NICKNAME("nickname", "昵称"),
    MOBILE("mobile", "手机号码"),
    EMAIL("email", "邮箱"),
    HEADER("header", "头像"),
    GENDER("gender", "性别"),
    BIRTH("birth", "生日"),
    CITY("city", "所在城市"),
    JOB("job", "职业"),
    SIGN("sign", "个性签名"),
    SOURCE_TYPE("source_type", "用户来源"),
    INTEGRATION("integration", "积分"),
    GROWTH("growth", "成长值"),
    STATUS("status", "启用状态"),
    CREATE_TIME("create_time", "注册时间");

    private String code;

    private String desc;

    MemberExcelHeadersEnum(String code, String desc) {
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