/**
 * Copyright 2019 bejson.com
 */
package com.ypdaic.mymall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2019-11-26 10:50:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class SpuSaveVo {

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品描述
     */
    private String spuDescription;

    /**
     * 分类id
     */
    private Long catalogId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 商品重量
     */
    private BigDecimal weight;

    /**
     * 上架状态
     */
    private int publishStatus;

    /**
     * 商品介绍
     */
    private List<String> decript;


    /**
     * 商品图集
     */
    private List<String> images;

    /**
     *
     */
    private Bounds bounds;

    /**
     * 商品基础属性
     */
    private List<BaseAttrs> baseAttrs;

    /**
     * sku 列表
     */
    private List<Skus> skus;



}