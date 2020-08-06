package com.ypdaic.mymall.order.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosPropertySourceRepository;
import com.alibaba.cloud.nacos.client.NacosPropertySource;
import com.alibaba.cloud.nacos.refresh.NacosContextRefresher;
import com.alibaba.cloud.nacos.refresh.NacosRefreshHistory;
import com.alibaba.cloud.nacos.refresh.NacosRefreshProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class NacosConfig {


    @Bean
    public MyNacosContextRefresher nacosContextRefresher(
            NacosConfigProperties nacosConfigProperties,
            NacosRefreshProperties nacosRefreshProperties,
            NacosRefreshHistory refreshHistory) {
        return new MyNacosContextRefresher(nacosRefreshProperties, refreshHistory,
                nacosConfigProperties.configServiceInstance());
    }

    public static class MyNacosContextRefresher implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

        private final static Logger log = LoggerFactory
                .getLogger(NacosContextRefresher.class);

        private static final AtomicLong REFRESH_COUNT = new AtomicLong(0);

        private final NacosRefreshProperties refreshProperties;

        private final NacosRefreshHistory refreshHistory;

        private final ConfigService configService;

        private ApplicationContext applicationContext;

        private AtomicBoolean ready = new AtomicBoolean(false);

        private Map<String, Listener> listenerMap = new ConcurrentHashMap<>(16);

        private static ThreadLocal<Boolean> isHriyx = new ThreadLocal<Boolean>() {
            @Override
            protected Boolean initialValue() {
                return Boolean.valueOf(false);
            }
        };

        public boolean getHsyrixUpdate() {
            return isHriyx.get();
        }

        public MyNacosContextRefresher(NacosRefreshProperties refreshProperties,
                                     NacosRefreshHistory refreshHistory, ConfigService configService) {
            this.refreshProperties = refreshProperties;
            this.refreshHistory = refreshHistory;
            this.configService = configService;
        }

        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
            // many Spring context
            if (this.ready.compareAndSet(false, true)) {
                this.registerNacosListenersForApplications();
            }
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        private void registerNacosListenersForApplications() {
            if (refreshProperties.isEnabled()) {
                for (NacosPropertySource nacosPropertySource : NacosPropertySourceRepository
                        .getAll()) {

                    if (!nacosPropertySource.isRefreshable()) {
                        continue;
                    }

                    String dataId = nacosPropertySource.getDataId();
                    registerNacosListener(nacosPropertySource.getGroup(), dataId);
                }
            }
        }

        private void registerNacosListener(final String group, final String dataId) {

            Listener listener = listenerMap.computeIfAbsent(dataId, i -> new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    try {
                        refreshCountIncrement();
                        String md5 = "";
                        if (!StringUtils.isEmpty(configInfo)) {
                            try {
                                MessageDigest md = MessageDigest.getInstance("MD5");
                                md5 = new BigInteger(1, md.digest(configInfo.getBytes("UTF-8")))
                                        .toString(16);
                            }
                            catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                                log.warn("[Nacos] unable to get md5 for dataId: " + dataId, e);
                            }
                        }
                        if ("hryrix.yml".equals(dataId)) {
                            isHriyx.set(true);
                        }
                        refreshHistory.add(dataId, md5);
                        applicationContext.publishEvent(
                                new RefreshEvent(this, dataId, "Refresh Nacos config"));
                        if (log.isDebugEnabled()) {
                            log.debug("Refresh Nacos config group " + group + ",dataId" + dataId);
                        }
                    } finally {
                        isHriyx.remove();
                    }

                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });

            try {
                configService.addListener(dataId, group, listener);
            }
            catch (NacosException e) {
                e.printStackTrace();
            }
        }

        public static long getRefreshCount() {
            return REFRESH_COUNT.get();
        }

        public static void refreshCountIncrement() {
            REFRESH_COUNT.incrementAndGet();
        }
    }
}
