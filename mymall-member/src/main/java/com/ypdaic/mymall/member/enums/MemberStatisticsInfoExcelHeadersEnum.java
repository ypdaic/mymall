package com.ypdaic.mymall.member.enums;

/**
 *
 * 会员统计信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-11
 */
public enum MemberStatisticsInfoExcelHeadersEnum {

    MEMBER_ID("member_id", "会员id"),
    CONSUME_AMOUNT("consume_amount", "累计消费金额"),
    COUPON_AMOUNT("coupon_amount", "累计优惠金额"),
    ORDER_COUNT("order_count", "订单数量"),
    COUPON_COUNT("coupon_count", "优惠券数量"),
    COMMENT_COUNT("comment_count", "评价数"),
    RETURN_ORDER_COUNT("return_order_count", "退货数量"),
    LOGIN_COUNT("login_count", "登录次数"),
    ATTEND_COUNT("attend_count", "关注数量"),
    FANS_COUNT("fans_count", "粉丝数量"),
    COLLECT_PRODUCT_COUNT("collect_product_count", "收藏的商品数量"),
    COLLECT_SUBJECT_COUNT("collect_subject_count", "收藏的专题活动数量"),
    COLLECT_COMMENT_COUNT("collect_comment_count", "收藏的评论数量"),
    INVITE_FRIEND_COUNT("invite_friend_count", "邀请的朋友数量");

    private String code;

    private String desc;

    MemberStatisticsInfoExcelHeadersEnum(String code, String desc) {
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