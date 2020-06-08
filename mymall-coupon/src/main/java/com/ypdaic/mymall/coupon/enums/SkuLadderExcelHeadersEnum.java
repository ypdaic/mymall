package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 商品阶梯价格 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SkuLadderExcelHeadersEnum {

    SKU_ID("sku_id", "spu_id"),
    FULL_COUNT("full_count", "满几件"),
    DISCOUNT("discount", "打几折"),
    PRICE("price", "折后价"),
    ADD_OTHER("add_other", "是否叠加其他优惠[0-不可叠加，1-可叠加]");

    private String code;

    private String desc;

    SkuLadderExcelHeadersEnum(String code, String desc) {
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