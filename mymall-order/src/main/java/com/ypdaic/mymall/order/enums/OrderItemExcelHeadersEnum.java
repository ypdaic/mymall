package com.ypdaic.mymall.order.enums;

/**
 *
 * 订单项信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum OrderItemExcelHeadersEnum {

    ORDER_ID("order_id", "order_id"),
    ORDER_SN("order_sn", "order_sn"),
    SPU_ID("spu_id", "spu_id"),
    SPU_NAME("spu_name", "spu_name"),
    SPU_PIC("spu_pic", "spu_pic"),
    SPU_BRAND("spu_brand", "品牌"),
    CATEGORY_ID("category_id", "商品分类id"),
    SKU_ID("sku_id", "商品sku编号"),
    SKU_NAME("sku_name", "商品sku名字"),
    SKU_PIC("sku_pic", "商品sku图片"),
    SKU_PRICE("sku_price", "商品sku价格"),
    SKU_QUANTITY("sku_quantity", "商品购买的数量"),
    SKU_ATTRS_VALS("sku_attrs_vals", "商品销售属性组合（JSON）"),
    PROMOTION_AMOUNT("promotion_amount", "商品促销分解金额"),
    COUPON_AMOUNT("coupon_amount", "优惠券优惠分解金额"),
    INTEGRATION_AMOUNT("integration_amount", "积分优惠分解金额"),
    REAL_AMOUNT("real_amount", "该商品经过优惠后的分解金额"),
    GIFT_INTEGRATION("gift_integration", "赠送积分"),
    GIFT_GROWTH("gift_growth", "赠送成长值");

    private String code;

    private String desc;

    OrderItemExcelHeadersEnum(String code, String desc) {
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