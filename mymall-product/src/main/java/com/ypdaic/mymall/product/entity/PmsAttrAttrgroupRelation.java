package com.ypdaic.mymall.product.entity;

import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 属性&属性分组关联
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmsAttrAttrgroupRelation extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性分组id
     */
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    private Integer attrSort;


}
