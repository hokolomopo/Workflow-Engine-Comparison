package be.yelido.frameworktest.services;

import be.yelido.frameworktest.objects.BillingInfo;
import be.yelido.frameworktest.objects.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AcceptedPaymentService {
    private Logger log = LoggerFactory.getLogger(AcceptedPaymentService.class);


    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "AcceptedPayment")
    public void receiveMessage(BillingInfo info) {
    log.info("Accepted payment: " + info);
    }

}
