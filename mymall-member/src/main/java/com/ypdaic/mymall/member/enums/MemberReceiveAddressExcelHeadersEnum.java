package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员收货地址 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberReceiveAddressExcelHeadersEnum {

    MEMBER_ID("member_id", "member_id"),
    NAME("name", "收货人姓名"),
    PHONE("phone", "电话"),
    POST_CODE("post_code", "邮政编码"),
    PROVINCE("province", "省份/直辖市"),
    CITY("city", "城市"),
    REGION("region", "区"),
    DETAIL_ADDRESS("detail_address", "详细地址(街道)"),
    AREACODE("areacode", "省市区代码"),
    DEFAULT_STATUS("default_status", "是否默认");

    private String code;

    private String desc;

    MemberReceiveAddressExcelHeadersEnum(String code, String desc) {
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