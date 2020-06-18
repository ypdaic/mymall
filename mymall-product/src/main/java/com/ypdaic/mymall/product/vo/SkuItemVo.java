package com.ypdaic.mymall.product.vo;

import com.ypdaic.mymall.product.entity.SkuImages;
import com.ypdaic.mymall.product.entity.SkuInfo;
import com.ypdaic.mymall.product.entity.SpuInfoDesc;
import lombok.Data;

import java.util.List;

/**
 * 商品详情信息，包含商品基本属性，图片信息，分类信息，描述信息，规格
 */
@Data
public class SkuItemVo {
    //1. SKU基本信息获取，pms_sku_info
    SkuInfo info;

    // 有货无货
    boolean hasStock = true;

    //2.SKU的图片信息获取，pms_sku_images
    List<SkuImages> images;


    //3. 获取SPU销售信属性组合
    List<SkuItemSaleAttrVo> saleAttr;

    //4. 获取SPU的介绍
    SpuInfoDesc desp;


    //5. 获取SPU的规格参数信息
    List<SpuItemAttrGroupVo> groupAttrs;

}
