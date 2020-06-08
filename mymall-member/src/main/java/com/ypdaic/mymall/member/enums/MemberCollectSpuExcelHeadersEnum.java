package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员收藏的商品 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberCollectSpuExcelHeadersEnum {

    MEMBER_ID("member_id", "会员id"),
    SPU_ID("spu_id", "spu_id"),
    SPU_NAME("spu_name", "spu_name"),
    SPU_IMG("spu_img", "spu_img"),
    CREATE_TIME("create_time", "create_time");

    private String code;

    private String desc;

    MemberCollectSpuExcelHeadersEnum(String code, String desc) {
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