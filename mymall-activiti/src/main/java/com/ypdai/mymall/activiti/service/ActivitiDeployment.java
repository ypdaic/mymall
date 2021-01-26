package com.ypdai.mymall.activiti.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程定义部署
 */
@Service
public class ActivitiDeployment {

    // 获取资源服务
    @Autowired
    RepositoryService repositoryService;

    public void deployment() {
        try {

            // 获取资源服务对象
            // 进行部署
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addClasspathResource("processes/test.bpmn");
            deploymentBuilder.name("请假流程");
            Deployment deploy = deploymentBuilder.deploy();

            System.out.println(deploy.getName());
            System.out.println(deploy.getId());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 删除一个流程部署
     */
    public void deleteDeployment(String deloymentId) {
        repositoryService.deleteDeployment(deloymentId);

    }

    /**
     * 强制删除一个部署
     * @param deploymentId
     */
    public void forciblyDeleteDeployment(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
    }
}
