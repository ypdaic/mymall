package com.ypdaic.mymall.common.enums;

/**
 * @ClassName NeedAuthEnum
 * @Description TODO
 * @Author daiyanping
 * @Date 2019-09-16
 * @Version 0.1
 */
public enum NeedAuthEnum {
    // 不需要token
    NOT_TOKEN(10),
    // 需要token
    TOKEN(20),
    // 需要token和角色
    TOKEN_ROLE(30);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    NeedAuthEnum(Integer value) {
        this.value = value;
    }
}
