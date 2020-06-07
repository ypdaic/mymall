package com.ypdaic.mymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 品牌分类关联
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_category_brand_relation")
public class CategoryBrandRelation extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long catelogId;

    private String brandName;

    private String catelogName;


}
