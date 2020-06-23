package com.ypdaic.mymall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FareVo {

    private MemberReceiveAddressVo memberReceiveAddressVo;

    private BigDecimal fare;

}
