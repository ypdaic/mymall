package com.ypdaic.mymall.product.enums;

/**
 *
 * sku图片 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum SkuImagesExcelHeadersEnum {

    SKU_ID("sku_id", "sku_id"),
    IMG_URL("img_url", "图片地址"),
    IMG_SORT("img_sort", "排序"),
    DEFAULT_IMG("default_img", "默认图[0 - 不是默认图，1 - 是默认图]");

    private String code;

    private String desc;

    SkuImagesExcelHeadersEnum(String code, String desc) {
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