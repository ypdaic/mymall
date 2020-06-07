package com.ypdaic.mymall.product.enums;

/**
 *
 * 商品评价 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsSpuCommentExcelHeadersEnum {

    SKU_ID("sku_id", "sku_id"),
    SPU_ID("spu_id", "spu_id"),
    SPU_NAME("spu_name", "商品名字"),
    MEMBER_NICK_NAME("member_nick_name", "会员昵称"),
    STAR("star", "星级"),
    MEMBER_IP("member_ip", "会员ip"),
    CREATE_TIME("create_time", "创建时间"),
    SHOW_STATUS("show_status", "显示状态[0-不显示，1-显示]"),
    SPU_ATTRIBUTES("spu_attributes", "购买时属性组合"),
    LIKES_COUNT("likes_count", "点赞数"),
    REPLY_COUNT("reply_count", "回复数"),
    RESOURCES("resources", "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]"),
    CONTENT("content", "内容"),
    MEMBER_ICON("member_icon", "用户头像"),
    COMMENT_TYPE("comment_type", "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]");

    private String code;

    private String desc;

    PmsSpuCommentExcelHeadersEnum(String code, String desc) {
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