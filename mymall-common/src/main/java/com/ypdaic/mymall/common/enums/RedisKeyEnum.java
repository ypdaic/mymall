package com.ypdaic.mymall.common.enums;

public enum RedisKeyEnum {

    //token缓存
    PAPV3_ACCOUNT_TOKEN_PREFIX("PAPV3_ACCOUNT_TOKEN:", "papv3账号token缓存"),
    ACCOUNT_TOKEN_PREFIX("ACCOUNT_TOKEN:", "账号token缓存"),
    ACCOUNT_INFO("ACCOUNT_INFO:%s", "坐席信息，key为账号ID, 数据包含头像，昵称,状态（离线，小休，在线）"),
    APP_CACHE("APP_CACHE", "app缓存");


    private String prefix;
    private String desc;

    RedisKeyEnum(String prefix, String desc) {
        this.prefix = prefix;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrefix() {
        return prefix;
    }
}
