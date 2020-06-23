package com.ypdaic.mymall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FareVo {

    private MemberReceiveAddressVo memberReceiveAddressVo;

    private BigDecimal fare;

}
