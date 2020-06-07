package com.ypdaic.mymall.product.enums;

/**
 *
 * spu图片 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public enum PmsSpuImagesExcelHeadersEnum {

    SPU_ID("spu_id", "spu_id"),
    IMG_NAME("img_name", "图片名"),
    IMG_URL("img_url", "图片地址"),
    IMG_SORT("img_sort", "顺序"),
    DEFAULT_IMG("default_img", "是否默认图");

    private String code;

    private String desc;

    PmsSpuImagesExcelHeadersEnum(String code, String desc) {
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