package com.ypdaic.mymall.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("third-app-config")
public class ThirdAppProperties {

    private App weibo;

    @Data
    public static class App {

        private String appKey;

        private String appSecret;
    }
}
