package com.ypdaic.mymall.coupon.enums;

/**
 *
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 导出字段枚举
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public enum HomeSubjectExcelHeadersEnum {

    NAME("name", "专题名字"),
    TITLE("title", "专题标题"),
    SUB_TITLE("sub_title", "专题副标题"),
    STATUS("status", "显示状态"),
    URL("url", "详情连接"),
    SORT("sort", "排序"),
    IMG("img", "专题图片地址");

    private String code;

    private String desc;

    HomeSubjectExcelHeadersEnum(String code, String desc) {
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