package com.ypdai.mymall.activiti.controller;

import com.ypdai.mymall.activiti.service.ActivitiDeployment;
import com.ypdai.mymall.activiti.service.ActivitiProcessQueryService;
import com.ypdai.mymall.activiti.service.ActivitiRuntime;
import com.ypdai.mymall.activiti.service.ActivitiTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activiti")
public class ActivitiController {

    @Autowired
    ActivitiDeployment activitiDeployment;

    @Autowired
    ActivitiRuntime activitiRuntime;

    @Autowired
    ActivitiTaskService activitiTaskService;

    @Autowired
    ActivitiProcessQueryService activitiProcessQueryService;

    /**
     * 流程部署测试
     */
    @PostMapping("/deployment")
    public void deployment() {
        activitiDeployment.deployment();
    }

    @PostMapping("/startProcess")
    public void startProcess() {
        activitiRuntime.startProcess();
    }

    @PostMapping("/queryTask")
    public void queryTask() {
        activitiTaskService.queryTask("张三");
    }

    /**
     * 任务执行
     */
    @PostMapping("/taskExcution/{taskId}")
    public void taskExcution(@PathVariable("taskId") String taskId) {
        activitiTaskService.taskExcution(taskId);
    }

    @PostMapping("/queryProessDefinition")
    public void queryProessDefinition() {
        activitiProcessQueryService.queryProcessDefinition();

    }

    /**
     * 流程实例都完成了才能删除，否者不可删除
     * @param deploymentId
     */
    @PostMapping("/deleteDeployment/{deploymentId}")
    public void deleteDeployment(@PathVariable("deploymentId") String deploymentId) {
        activitiDeployment.deleteDeployment(deploymentId);
    }

    /**
     * 强制删除还么有走完流程的部署
     * @param deploymentId
     */
    @PostMapping("/forciblyDeleteDeployment/{deploymentId}")
    public void forciblyDeleteDeployment(@PathVariable("deploymentId") String deploymentId) {
        activitiDeployment.forciblyDeleteDeployment(deploymentId);
    }

}
