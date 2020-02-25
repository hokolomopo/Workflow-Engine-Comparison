package be.yelido.frameworktest.routes;

import be.yelido.frameworktest.objects.Order;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebRouter {

    @Autowired
    private RuntimeService runtimeService;

    @Value("${input.queue}")
    String inputQueue;

    private Logger logger = LoggerFactory.getLogger(WebRouter.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/")
    Order home() {
        return new Order("Jean", "vip", "book", 10);
    }

    @PostMapping("/order")
    void orderProduct(@RequestBody Order order){
        logger.info("Received /order message");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess");
        runtimeService.instance
    }
}
