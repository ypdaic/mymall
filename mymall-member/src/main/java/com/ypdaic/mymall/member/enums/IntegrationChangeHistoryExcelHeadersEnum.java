package com.ypdaic.mymall.member.enums;

/**
 *
 * 积分变化历史记录 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum IntegrationChangeHistoryExcelHeadersEnum {

    MEMBER_ID("member_id", "member_id"),
    CREATE_TIME("create_time", "create_time"),
    CHANGE_COUNT("change_count", "变化的值"),
    NOTE("note", "备注"),
    SOURCE_TYOE("source_tyoe", "来源[0->购物；1->管理员修改;2->活动]");

    private String code;

    private String desc;

    IntegrationChangeHistoryExcelHeadersEnum(String code, String desc) {
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