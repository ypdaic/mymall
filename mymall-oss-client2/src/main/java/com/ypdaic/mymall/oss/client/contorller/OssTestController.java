package com.ypdaic.mymall.oss.client.contorller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping
public class OssTestController {

    @Value("${oss.server.url}")
    private String ossServerUrl;

    @Value("${oss.server.tokenInfoUrl}")
    private String tokenInfoUrl;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 无需登录就可访问
     * @return
     */
    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    /**
     * 需要登录访问
     * @param model
     * @return
     */
    @GetMapping("/boss")
    public String employess(Model model, HttpSession httpSession, @RequestParam(value = "token", required = false) String token) {
        if (!StringUtils.isEmpty(token)) {
            // 去服务端获取user信息
//            Object user = redisTemplate.opsForValue().get("oss:user:" + token);
            ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(tokenInfoUrl, JSONObject.class, token);
            httpSession.setAttribute("loginUser", forEntity.getBody());

        }
        Object loginUser = httpSession.getAttribute("loginUser");
        if (Objects.isNull(loginUser)) {
            // 没登录，跳转到登录服务器去登录
            return "redirect:" + ossServerUrl + "?redirect_url=http://client2.com:8093/boss";
        } else {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("张三");
            strings.add("李思");
            model.addAttribute("emps", strings);
            model.addAttribute("user", loginUser);
            return "list";
        }

    }
}
