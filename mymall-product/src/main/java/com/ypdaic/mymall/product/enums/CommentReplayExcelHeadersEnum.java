package com.ypdaic.mymall.product.enums;

/**
 *
 * 商品评价回复关系 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum CommentReplayExcelHeadersEnum {

    COMMENT_ID("comment_id", "评论id"),
    REPLY_ID("reply_id", "回复id");

    private String code;

    private String desc;

    CommentReplayExcelHeadersEnum(String code, String desc) {
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