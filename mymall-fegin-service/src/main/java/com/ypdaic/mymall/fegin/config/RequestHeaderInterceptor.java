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
            /**
             * 无需复制所有的请求头，由于
             * 在 RequestInterceptor 接口中将请求携带的内容复制出来返回给Feign 带到B服务器（其中包括了content-length）。
             *
             * 5、在B服务接到content-length 与实际传的content 长度是不一致的。所以导致feign.FeignException: status 400 reading
             */
//            Enumeration<String> headerNames = request.getHeaderNames();
//            if (Objects.nonNull(headerNames)) {
//                // 同步请求头数据
//                while (headerNames.hasMoreElements()) {
//                    String headerName = headerNames.nextElement();
//                    String header = request.getHeader(headerName);
//                    template.header(headerName, header);
//
//                }
//            }

            String cookie = request.getHeader("Cookie");
            template.header("Cookie", cookie);
        }

    }
}
