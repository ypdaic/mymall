package com.ypdaic.mymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * spu属性值
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product_attr_value")
public class ProductAttrValue extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 顺序
     */
    private Integer attrSort;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    private Integer quickShow;


}
