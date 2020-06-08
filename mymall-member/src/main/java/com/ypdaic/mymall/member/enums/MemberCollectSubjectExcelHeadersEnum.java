package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员收藏的专题活动 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum MemberCollectSubjectExcelHeadersEnum {

    SUBJECT_ID("subject_id", "subject_id"),
    SUBJECT_NAME("subject_name", "subject_name"),
    SUBJECT_IMG("subject_img", "subject_img"),
    SUBJECT_URLL("subject_urll", "活动url");

    private String code;

    private String desc;

    MemberCollectSubjectExcelHeadersEnum(String code, String desc) {
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