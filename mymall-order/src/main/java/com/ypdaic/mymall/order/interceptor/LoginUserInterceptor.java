package com.ypdaic.mymall.order.interceptor;

import com.ypdaic.mymall.common.to.MemberRespVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberRespVo> threadLocal = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        MemberRespVo loginUser = (MemberRespVo) session.getAttribute("loginUser");
        if (Objects.isNull(loginUser)){
            request.getSession().setAttribute("msg", "请先进行登录");
            response.sendRedirect("http://auth.mymall.com/login.html");
            return false;
        }
        threadLocal.set(loginUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        threadLocal.remove();
    }
}
