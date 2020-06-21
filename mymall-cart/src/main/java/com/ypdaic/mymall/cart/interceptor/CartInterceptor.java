package com.ypdaic.mymall.cart.interceptor;

import cn.hutool.core.lang.UUID;
import com.ypdaic.mymall.cart.vo.UserInfo;
import com.ypdaic.mymall.common.constant.CartConstant;
import com.ypdaic.mymall.common.to.MemberRespVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class CartInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfoVo = new UserInfo();
        HttpSession session = request.getSession();
        MemberRespVo loginUser = (MemberRespVo) session.getAttribute("loginUser");
        if (Objects.nonNull(loginUser)) {
            userInfoVo.setUserId(loginUser.getId());

        }

        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies) && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // user-key
                String name = cookie.getName();
                if (CartConstant.TEMP_USER_COOKIE_NAME.equals(name)) {
                    userInfoVo.setUserKey(cookie.getValue());
                    userInfoVo.setTempUser(true);
                }
            }
        }
        // 如果没有临时用户一定分配一个
        if (StringUtils.isEmpty(userInfoVo.getUserKey())) {
            String uuid = UUID.fastUUID().toString();
            userInfoVo.setUserKey(uuid);
        }
        threadLocal.set(userInfoVo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try {
            UserInfo userInfo = threadLocal.get();
            // 如果没哟临时用户，一定保存临时用户
            if (!userInfo.getTempUser().booleanValue()) {
                Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, userInfo.getUserKey());
                cookie.setDomain("mymall.com");
                cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);
                response.addCookie(cookie);
            }
        } finally {
            threadLocal.remove();
        }


    }
}
