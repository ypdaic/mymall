package com.ypdaic.mymall.ware.enums;

/**
 *
 * 仓库信息 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum WareInfoExcelHeadersEnum {

    NAME("name", "仓库名"),
    ADDRESS("address", "仓库地址"),
    AREACODE("areacode", "区域编码");

    private String code;

    private String desc;

    WareInfoExcelHeadersEnum(String code, String desc) {
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