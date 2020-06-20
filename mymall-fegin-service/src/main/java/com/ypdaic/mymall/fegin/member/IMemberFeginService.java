package com.ypdaic.mymall.fegin.member;

import com.ypdaic.mymall.common.to.WeiboOauth2UserVo;
import com.ypdaic.mymall.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "mymall-member", path = "/member/member")
public interface IMemberFeginService {

    @PostMapping("/oauth2/login")
    R login(WeiboOauth2UserVo weiboOauth2User);
}
