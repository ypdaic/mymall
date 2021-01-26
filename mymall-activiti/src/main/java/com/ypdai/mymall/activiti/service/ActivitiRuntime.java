package com.ypdai.mymall.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiRuntime {

    @Autowired
    RuntimeService runtimeService;

    /**
     * 启动一个流程实例
     * 背后影响的表
     *
     * act_ru_execution执行表   act_ru_identitylink 参与者信息 act_ru_task 任务实例
     *
     */
    public void startProcess() {
        try {
            // 启动一个流程实例
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");

            // 打印流程部署id
            System.out.println(processInstance.getDeploymentId());

            // 打印流程实例id
            System.out.println(processInstance.getId());

            // 打印活动id
            System.out.println(processInstance.getActivityId());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
