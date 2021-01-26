package com.ypdai.mymall.activiti.service;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiTaskService {

    @Autowired
    TaskService taskService;

    public void queryTask(String user) {
        List<Task> list = taskService.createTaskQuery().processDefinitionKey("myProcess_1").taskCandidateOrAssigned("张三").list();

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("任务id:" + task.getId());
            System.out.println("任务负责人:" + task.getAssignee());
            System.out.println("任务名称:" + task.getName());
        }

    }

    /**
     * 查询并完成任务
     * @param user
     */
    public void queryOneTaskAndComplete(String user) {
        Task task = taskService.createTaskQuery().processDefinitionKey("myProcess_1").taskCandidateOrAssigned("王五").singleResult();
        taskService.complete(task.getId());
    }

    // 任务执行
    public void taskExcution(String taskId) {
        taskService.complete(taskId);
        System.out.println("完成任务id:" + taskId);
    }
}
