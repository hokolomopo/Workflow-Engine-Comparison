package be.yelido.frameworktest;


import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.ActivitiTestCase;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MyBusinessProcessTest {

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    @Deployment
    public void ruleUsageExample() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess");
//
//        ProcessInstance i2 = runtimeService.createProcessInstanceQuery().active().singleResult();
//
//        assertEquals(instance.getId(), i2.getId());
//        TaskService taskService = activitiRule.getTaskService();
//        Task task = taskService.createTaskQuery().singleResult();
////        assertEquals("My Task", task.getName());
//        System.out.println(task != null);
//
////        taskService.complete(task.getId());
//        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}
