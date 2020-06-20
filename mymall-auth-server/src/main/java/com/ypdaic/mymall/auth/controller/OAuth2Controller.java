package com.ypdaic.mymall.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.auth.properties.ThirdAppProperties;
import com.ypdaic.mymall.common.to.MemberRespVo;
import com.ypdaic.mymall.common.to.WeiboOauth2UserVo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.member.IMemberFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping
public class OAuth2Controller {

    @Autowired
    ThirdAppProperties thirdAppProperties;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IMemberFeginService memberFeginService;

    @GetMapping("/oauth2/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession httpSession) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("client_id", thirdAppProperties.getWeibo().getAppKey());
//        jsonObject.put("client_secret", thirdAppProperties.getWeibo().getAppSecret());
//        jsonObject.put("grant_type", "authorization_code");
//        jsonObject.put("redirect_uri", "http://auth.mymall.com/oauth2/weibo/success");
//        jsonObject.put("code", code);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange("https://api.weibo.com/oauth2/access_token?client_id={client_id}&client_secret={client_secret}&grant_type={grant_type}&redirect_uri={redirect_uri}&code={code}", HttpMethod.POST, new HttpEntity<>(null), JSONObject.class,
                thirdAppProperties.getWeibo().getAppKey(),
                thirdAppProperties.getWeibo().getAppSecret(),
                "authorization_code",
                "http://auth.mymall.com/oauth2/weibo/success",
                code);
        if (responseEntity.getStatusCode().value() == HttpStatus.OK.value()) {
            JSONObject body = responseEntity.getBody();
            WeiboOauth2UserVo weiboOauth2User = body.toJavaObject(WeiboOauth2UserVo.class);
            log.info("开始登录", weiboOauth2User);
            R login = memberFeginService.login(weiboOauth2User);
            if (login.getCode() == 0) {
                MemberRespVo data = login.getData("data", new TypeReference<MemberRespVo>(){});
                log.info("用户登录成功", data);

                // 1 第一次使用session: 命令浏览器保存卡号，JSESSIONID 这个cookie
                // 以后浏览器访问那个网站就会带上这个网站的cookie
                // 子域之间，mymall.com auth.mymall.com order.mymall.com
                // 发卡的时候（域名指定为父域名），即使是子域系统发的卡，也能让父域直接使用

                httpSession.setAttribute("loginUser", data);
                return "redirect:http://mymall.com";
            }
        } else {
            log.error("用户登录失败", responseEntity.getBody());
        }
        return "redirect:http://auth.mymall.com/login.html";

    }
}
