package com.ypdaic.mymall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单购物项
 */
public class OrderItemVo {

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String image;

    /**
     * sku属性
     */
    private List<String> skuAttr;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 商品重量
     */
    private BigDecimal weight;

    /**
     * TODO 是否有货待处理
     */
    private boolean hasStock = true;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(List<String> skuAttr) {
        this.skuAttr = skuAttr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal("" + count));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }
}
