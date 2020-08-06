package com.ypdaic.mymall.product.config;

import com.alibaba.cloud.nacos.refresh.NacosContextRefresher;
import com.netflix.hystrix.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HsyrixConfig implements ApplicationListener<EnvironmentChangeEvent> {

    @Autowired
//    @Qualifier("MyNacosContextRefresher")
    NacosContextRefresher nacosContextRefresher;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        NacosConfig.MyNacosContextRefresher myNacosContextRefresher = (NacosConfig.MyNacosContextRefresher) nacosContextRefresher;
        boolean hsyrixUpdate = myNacosContextRefresher.getHsyrixUpdate();
        if (hsyrixUpdate) {
            Hystrix.reset();
        }
    }
}
