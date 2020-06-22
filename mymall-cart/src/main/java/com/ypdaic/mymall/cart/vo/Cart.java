package com.ypdaic.mymall.cart.vo;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 整个购物车
 */
public class Cart {

    private List<CartItem> items;

    /**
     * 商品数量
     */
    private Integer countNum;

    /**
     * 商品类型数量
     */
    private Integer countType;

    /**
     * 商品总价
     */
    private BigDecimal totalAmount;

    /**
     * 减免价格
     */
    private BigDecimal reduce = new BigDecimal(0);

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getCountNum() {
        int countNum = 0;
        if (CollectionUtils.isNotEmpty(items)) {
            for (CartItem item : items) {
                Integer count = item.getCount();
                countNum += count;
            }
        }
        return countNum;
    }

    public Integer getCountType() {
        int countType = 0;
        if (CollectionUtils.isNotEmpty(items)) {
            for (CartItem item : items) {
                countType += 1;
            }
        }
        return countType;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(items)) {
            for (CartItem item : items) {
                if(item.getCheck()) {

                    BigDecimal totalPrice = item.getTotalPrice();
                    totalAmount = totalAmount.add(totalPrice);
                }
            }
        }
        totalAmount = totalAmount.subtract(reduce);
        return totalAmount;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
