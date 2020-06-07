package com.ypdaic.mymall.product.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * spu信息
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PmsSpuInfo extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品描述
     */
    private String spuDescription;

    /**
     * 所属分类id
     */
    private Long catalogId;

    /**
     * 品牌id
     */
    private Long brandId;

    private BigDecimal weight;

    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    private Integer publishStatus;

    private Date createTime;

    private Date updateTime;


}
