package be.yelido.frameworktest.services;

import be.yelido.frameworktest.objects.BillingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class RefusedPaymentService {
    private Logger log = LoggerFactory.getLogger(RefusedPaymentService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "RefusedPayment")
    public void receiveMessage(BillingInfo info) {
        log.info("Refused payment: " + info);
    }

}
