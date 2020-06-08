package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 商品满减信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SkuFullReductionExcelHeadersEnum {

    SKU_ID("sku_id", "spu_id"),
    FULL_PRICE("full_price", "满多少"),
    REDUCE_PRICE("reduce_price", "减多少"),
    ADD_OTHER("add_other", "是否参与其他优惠");

    private String code;

    private String desc;

    SkuFullReductionExcelHeadersEnum(String code, String desc) {
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