package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 秒杀活动商品关联 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SeckillSkuRelationExcelHeadersEnum {

    PROMOTION_ID("promotion_id", "活动id"),
    PROMOTION_SESSION_ID("promotion_session_id", "活动场次id"),
    SKU_ID("sku_id", "商品id"),
    SECKILL_PRICE("seckill_price", "秒杀价格"),
    SECKILL_COUNT("seckill_count", "秒杀总量"),
    SECKILL_LIMIT("seckill_limit", "每人限购数量"),
    SECKILL_SORT("seckill_sort", "排序");

    private String code;

    private String desc;

    SeckillSkuRelationExcelHeadersEnum(String code, String desc) {
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