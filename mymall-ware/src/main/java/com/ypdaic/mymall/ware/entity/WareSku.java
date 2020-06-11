package com.ypdaic.mymall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ypdaic.mymall.common.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 商品库存
 *
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_ware_sku")
public class WareSku extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * sku_name
     */
    private String skuName;

    /**
     * 锁定库存
     */
    private Integer stockLocked;


}
