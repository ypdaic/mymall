package com.ypdaic.mymall.fegin.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

public class RequestHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (Objects.nonNull(headerNames)) {
                // 同步请求头数据
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    String header = request.getHeader(headerName);
                    template.header(headerName, header);

                }
            }
        }

    }
}
