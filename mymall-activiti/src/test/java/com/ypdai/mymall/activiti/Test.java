package com.ypdai.mymall.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class Test {

    @org.junit.Test
    public void test() {
        // 1. 创建ProcessEngineConfiguration 对象
        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        // 2. 创建ProcessEngine对象
        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();



    }
}
