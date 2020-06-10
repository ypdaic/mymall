package com.ypdaic.mymall.product.enums;

/**
 *
 * spu信息介绍 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-10
 */
public enum SpuInfoDescExcelHeadersEnum {

    SPU_ID("spu_id", "商品id"),
    DECRIPT("decript", "商品介绍");

    private String code;

    private String desc;

    SpuInfoDescExcelHeadersEnum(String code, String desc) {
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