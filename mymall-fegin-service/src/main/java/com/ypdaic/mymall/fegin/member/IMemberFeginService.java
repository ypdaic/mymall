package com.ypdaic.mymall.fegin.member;

import com.ypdaic.mymall.common.to.WeiboOauth2UserVo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "mymall-member", configuration = {FeginConfig.class})
public interface IMemberFeginService {

    @PostMapping("/member/member/oauth2/login")
    R login(WeiboOauth2UserVo weiboOauth2User);

    @GetMapping("/member/member-receive-address/{memberId}/addresses")
    R getAddress(@PathVariable("memberId") Long memberId);

    @RequestMapping("/member/member-receive-address/info/{id}")
    R info(@PathVariable("id") Long id);
}
