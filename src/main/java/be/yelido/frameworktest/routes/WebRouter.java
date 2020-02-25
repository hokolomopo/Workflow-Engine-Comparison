package be.yelido.frameworktest.routes;

import be.yelido.frameworktest.objects.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebRouter {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Value("${input.queue}")
    String inputQueue;

    private Logger logger = LoggerFactory.getLogger(WebRouter.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/history")
    String history() {
        try {
//            System.out.println(historyService.createHistoricVariableInstanceQuery().list().size());
            System.out.println(historyService.createNativeHistoricProcessInstanceQuery().list().size());

            List<HistoricProcessInstance> l = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("SimpleShopTest").list();
            return Integer.toString(l.size());
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/instances")
    String instances() {
        return Integer.toString(runtimeService.createProcessInstanceQuery().list().size());
    }

    @PostMapping("/order")
    void orderProduct(@RequestBody Order order){
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> variables = oMapper.convertValue(order, Map.class);
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey("SimpleShopTest", variables);

//        logger.info("Received /order message");
////        jmsTemplate.convertAndSend(inputQueue, order);
//        Map<String, Object> variables = new HashMap<>();
//
//        ObjectValue orderValue = Variables.objectValue(order)
//                .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
//                .create();
//
//        variables.put("order", orderValue);

        runtimeService.startProcessInstanceByKey("SimpleShopTest", variables);


    }
}
