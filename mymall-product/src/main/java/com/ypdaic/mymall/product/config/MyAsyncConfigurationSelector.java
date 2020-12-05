package com.ypdaic.mymall.product.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.ProxyAsyncConfiguration;

public class MyAsyncConfigurationSelector extends AsyncConfigurationSelector {

    private static final String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME =
            "org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";

    /**
     * Returns {@link ProxyAsyncConfiguration} or {@code AspectJAsyncConfiguration}
     * for {@code PROXY} and {@code ASPECTJ} values of {@link EnableAsync#mode()},
     * respectively.
     */
    @Override
    @Nullable
    public String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
                return new String[] {MyAsyncConfig.class.getName()};
            case ASPECTJ:
                return new String[] {ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME};
            default:
                return null;
        }
    }
}
