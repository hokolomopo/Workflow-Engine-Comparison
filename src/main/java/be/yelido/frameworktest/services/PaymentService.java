package be.yelido.frameworktest.services;

import be.yelido.frameworktest.objects.BillingInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class PaymentService {
    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${payment.queue.output}")
    String outputQueue;

    @JmsListener(destination = "PaymentService")
    public void receiveMessage(BillingInfo info) {
        log.info("Process bill: " + info);
        processBill(info);
        confirmBill(info);
    }

    public void processBill(BillingInfo info){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        info.setBillingInfo(generatedString);

        if(random.nextFloat() > 0.5)
            info.setAccepted(true);
    }

    private void confirmBill(BillingInfo info){
    }
}
