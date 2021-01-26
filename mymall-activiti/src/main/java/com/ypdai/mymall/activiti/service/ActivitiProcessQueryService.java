package com.ypdai.mymall.activiti.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ActivitiProcessQueryService {

    @Autowired
    RepositoryService repositoryService;

    /**
     * 查询流程定义基本信息
     */
    public void queryProcessDefinition() {
        // 得到流程定义查询器
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> processDefinitions = processDefinitionQuery.processDefinitionKey("myProcess_1").orderByProcessDefinitionVersion().asc().list();

        if (!CollectionUtils.isEmpty(processDefinitions)) {

            for (ProcessDefinition processDefinition : processDefinitions) {
                System.out.println("流程定义名称:" + processDefinition.getName());
                System.out.println("流程定义id:" + processDefinition.getId());
                System.out.println("流程定义版本号:" + processDefinition.getVersion());
            }
            System.out.println();
        }


    }
}
