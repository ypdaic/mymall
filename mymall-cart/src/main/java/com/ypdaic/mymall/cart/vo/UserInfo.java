package com.ypdaic.mymall.cart.vo;

import lombok.Data;

@Data
public class UserInfo {
    private Long userId;

    private String userKey;

    /**
     * 是否是临时用户
     */
    private Boolean tempUser = false ;
}
