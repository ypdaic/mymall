package com.ypdaic.mymall.order.vo;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单确认页需要用的数据
 */
public class OrderConfirmVo {

    // 收货地址
    private List<MemberReceiveAddressVo> memberReceiveAddressVoList;

    // 所有选中的购物项
    private List<OrderItemVo> orderItemVos;

    // 发票信息

    /**
     * 优惠
     */
    private Integer integration;

    // 订单总额
    private BigDecimal total = new BigDecimal(0);

    // 订单防重令牌
    private String orderToken;

    //商品总件数
    private Integer count = Integer.valueOf(0);

    public List<MemberReceiveAddressVo> getMemberReceiveAddressVoList() {
        return memberReceiveAddressVoList;
    }

    public void setMemberReceiveAddressVoList(List<MemberReceiveAddressVo> memberReceiveAddressVoList) {
        this.memberReceiveAddressVoList = memberReceiveAddressVoList;
    }

    public List<OrderItemVo> getOrderItemVos() {
        return orderItemVos;
    }

    public void setOrderItemVos(List<OrderItemVo> orderItemVos) {
        this.orderItemVos = orderItemVos;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        if(CollectionUtils.isNotEmpty(orderItemVos)) {
            for (OrderItemVo orderItemVo : orderItemVos) {
                BigDecimal totalPrice = orderItemVo.getTotalPrice();
                total = total.add(totalPrice);

            }
        }
        return total;
    }

    public BigDecimal getPayPrice() {
        BigDecimal total = getTotal();
        payPrice = total;
        return payPrice;
    }

    // 应付总额
    BigDecimal payPrice;


    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public Integer getCount() {
        if (CollectionUtils.isNotEmpty(orderItemVos)) {
            for (OrderItemVo orderItemVo : orderItemVos) {
                Integer count = orderItemVo.getCount();
                this.count += count;
            }
        }
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
