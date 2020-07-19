package com.ypdaic.mymall.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.ypdaic.mymall.common.exception.BizCodeEnum;
import com.ypdaic.mymall.common.util.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SentinelConfig {

    public SentinelConfig() {
        /**
         * sentinel 降级配置
         */
        WebCallbackManager.setUrlBlockHandler(new UrlBlockHandler() {
            @Override
            public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
                R error = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setHeader("contentType", "application/json");
                httpServletResponse.getWriter().print(JSONObject.toJSONString(error));
            }
        });
    }
}
