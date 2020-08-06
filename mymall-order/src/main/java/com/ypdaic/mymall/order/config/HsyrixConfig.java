package com.ypdaic.mymall.order.config;

import com.alibaba.cloud.nacos.refresh.NacosContextRefresher;
import com.netflix.hystrix.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HsyrixConfig implements ApplicationListener<EnvironmentChangeEvent> {

    @Autowired
    NacosConfig.MyNacosContextRefresher nacosContextRefresher;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        boolean hsyrixUpdate = nacosContextRefresher.getHsyrixUpdate();
        if (hsyrixUpdate) {
            Hystrix.reset();
        }
    }
}
