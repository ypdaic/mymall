package com.ypdaic.mymall.product.enums;

/**
 *
 * sku信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsSkuInfoExcelHeadersEnum {

    SKU_ID("sku_id", "skuId"),
    SPU_ID("spu_id", "spuId"),
    SKU_NAME("sku_name", "sku名称"),
    SKU_DESC("sku_desc", "sku介绍描述"),
    CATALOG_ID("catalog_id", "所属分类id"),
    BRAND_ID("brand_id", "品牌id"),
    SKU_DEFAULT_IMG("sku_default_img", "默认图片"),
    SKU_TITLE("sku_title", "标题"),
    SKU_SUBTITLE("sku_subtitle", "副标题"),
    PRICE("price", "价格"),
    SALE_COUNT("sale_count", "销量");

    private String code;

    private String desc;

    PmsSkuInfoExcelHeadersEnum(String code, String desc) {
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