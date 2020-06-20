package com.ypdaic.mymall.oss.server.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("url") String redirectUrl,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletResponse response) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set("oss:user:" + token, jsonObject);
            // 设置cookie，这里是单点登录的关键
            Cookie cookie = new Cookie("sso_token", token);
            response.addCookie(cookie);
            // 登录成功跳转，跳回到登录页
            return "redirect:" + redirectUrl + "?token=" + token ;
        }

        // 登录失败，展示登录页
        return "login";

    }

    @GetMapping("/login.html")
    public String loginHtml(@RequestParam("redirect_url") String url, Model model, @CookieValue(value = "sso_token", required = false) String ssoToken) {
        if (!StringUtils.isEmpty(ssoToken)) {
            // 说明之前有人登录过，浏览器留下了痕迹，直接跳转回去
            return "redirect:" + url + "?token=" + ssoToken;
        }
        model.addAttribute("url", url);
        return "login";
    }

    @ResponseBody
    @GetMapping("/tokenInfo")
    public Object loginHtml(@RequestParam("token") String token) {
        Object o = redisTemplate.opsForValue().get(("oss:user:" + token));
        return o;

    }
}
