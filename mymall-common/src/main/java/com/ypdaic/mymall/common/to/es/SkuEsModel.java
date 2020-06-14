package com.ypdaic.mymall.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuEsModel {

    /**
     * skuid
     */
    private Long skuId;

    /**
     * spuid
     */
    private Long spuId;

    /**
     * sku标题
     */
    private String skuTitle;

    /**
     * sku价格
     */
    private BigDecimal skuPrice;

    /**
     * sku图片
     */
    private String  skuImg;

    /**
     * 销量
     */
    private Long saleCount;

    /**
     * 热度
     */
    private Long hotScore;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long catelogId;

    /**
     * 品牌名称
     */
    private String  brandName;

    /**
     * 品牌图片
     */
    private String  brandImg;

    /**
     * 分类名称
     */
    private String  catelogName;

    private List<Attrs> attrs;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

    @Data
    public static class Attrs {
        /**
         * 属性id
         */
        private Long attrId;

        /**
         * 属性名称
         */
        private String  attrName;

        /**
         * 属性值
         */
        private String  attrValue;
    }
}
