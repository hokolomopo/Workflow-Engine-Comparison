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

import java.util.Random;

@Service
public class NormalShop {
    private Logger log = LoggerFactory.getLogger(NormalShop.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${shop.queue.output}")
    String outputQueue;

    @JmsListener(destination = "NormalShop")
    public void receiveMessage(Order order) {
        log.info("Order in shop: " + order);
        processOrder(order);
        sendOrderToPay(order);
    }

    public void processOrder(Order order){
        Random r = new Random();
        order.setPrice(r.nextInt());
    }

    private void sendOrderToPay(Order order){
        jmsTemplate.convertAndSend(outputQueue,
                new BillingInfo(order.getClientName(), order.getPrice()));
    }
}
