package com.ypdaic.mymall.product.enums;

/**
 *
 * spu信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SpuInfoExcelHeadersEnum {

    SPU_NAME("spu_name", "商品名称"),
    SPU_DESCRIPTION("spu_description", "商品描述"),
    CATALOG_ID("catalog_id", "所属分类id"),
    BRAND_ID("brand_id", "品牌id"),
    WEIGHT("weight", ""),
    PUBLISH_STATUS("publish_status", "上架状态[0 - 下架，1 - 上架]"),
    CREATE_TIME("create_time", ""),
    UPDATE_TIME("update_time", "");

    private String code;

    private String desc;

    SpuInfoExcelHeadersEnum(String code, String desc) {
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