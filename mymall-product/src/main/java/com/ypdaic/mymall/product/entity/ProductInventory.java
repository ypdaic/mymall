package com.ypdaic.mymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ypdaic.mymall.common.base.SuperEntity;

/**
 * 库存数量model
 * @author Administrator
 *
 */
@TableName("pms_product_inventory")
public class ProductInventory extends SuperEntity {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 库存数量
	 */
	private Long inventoryCnt;
	
	public ProductInventory() {
		
	}
	
	public ProductInventory(Integer productId, Long inventoryCnt) {
		this.productId = productId;
		this.inventoryCnt = inventoryCnt;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Long getInventoryCnt() {
		return inventoryCnt;
	}
	public void setInventoryCnt(Long inventoryCnt) {
		this.inventoryCnt = inventoryCnt;
	}
	
}
