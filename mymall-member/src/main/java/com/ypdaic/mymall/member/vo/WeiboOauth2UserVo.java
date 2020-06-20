package com.ypdaic.mymall.member.vo;

import lombok.Data;

@Data
public class WeiboOauth2UserVo {

    private String access_token;
    private String remind_in;
    private long expires_in;
    private String uid;
    private String isRealName;
}
