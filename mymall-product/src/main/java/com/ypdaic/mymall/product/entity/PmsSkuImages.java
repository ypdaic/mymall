package com.ypdaic.mymall.product.entity;

import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * sku图片
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmsSkuImages extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 排序
     */
    private Integer imgSort;

    /**
     * 默认图[0 - 不是默认图，1 - 是默认图]
     */
    private Integer defaultImg;


}
