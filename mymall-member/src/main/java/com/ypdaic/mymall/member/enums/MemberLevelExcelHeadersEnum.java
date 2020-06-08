package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员等级 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberLevelExcelHeadersEnum {

    NAME("name", "等级名称"),
    GROWTH_POINT("growth_point", "等级需要的成长值"),
    DEFAULT_STATUS("default_status", "是否为默认等级[0->不是；1->是]"),
    FREE_FREIGHT_POINT("free_freight_point", "免运费标准"),
    COMMENT_GROWTH_POINT("comment_growth_point", "每次评价获取的成长值"),
    PRIVILEDGE_FREE_FREIGHT("priviledge_free_freight", "是否有免邮特权"),
    PRIVILEDGE_MEMBER_PRICE("priviledge_member_price", "是否有会员价格特权"),
    PRIVILEDGE_BIRTHDAY("priviledge_birthday", "是否有生日特权"),
    NOTE("note", "备注");

    private String code;

    private String desc;

    MemberLevelExcelHeadersEnum(String code, String desc) {
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