package be.yelido.frameworktest.routes;

import be.yelido.frameworktest.objects.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class WebRouter {

    String testId;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Value("${input.queue}")
    String inputQueue;

    private Logger logger = LoggerFactory.getLogger(WebRouter.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/instances")
    String home() {
        try {
            ProcessInstance l2 = runtimeService.createProcessInstanceQuery().processInstanceId(testId).singleResult();
            System.out.println(l2 != null);

            System.out.println();
            List<ProcessInstance> l = runtimeService.createProcessInstanceQuery().list();
            return Integer.toString(l.size());
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/history")
    String history() {
        try {
//            System.out.println(historyService.createHistoricVariableInstanceQuery().list().size());
            System.out.println(historyService.createNativeHistoricProcessInstanceQuery().list().size());

            HistoricProcessInstance why = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("SimpleShopTest").singleResult();

            HistoricProcessInstance l = historyService.createHistoricProcessInstanceQuery().processInstanceId
                    (testId).singleResult();
            return Boolean.toString((l != null));
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/order")
    void orderProduct(@RequestBody Order order){
        logger.info("Received /order message");
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> variables = oMapper.convertValue(order, Map.class);

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("SimpleShopTest", variables);
        testId = instance.getId();
    }

//    @GetMapping("/test")
//    String test(@Autowired ProcessEngineConfiguration processEngineConfiguration){
//        return processEngineConfiguration.getHistory();
//    }
}
