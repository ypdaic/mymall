package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 商品spu积分设置 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-10
 */
public enum SpuBoundsExcelHeadersEnum {

    SPU_ID("spu_id", ""),
    GROW_BOUNDS("grow_bounds", "成长积分"),
    BUY_BOUNDS("buy_bounds", "购物积分"),
    WORK("work", "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]");

    private String code;

    private String desc;

    SpuBoundsExcelHeadersEnum(String code, String desc) {
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