package be.yelido.frameworktest.javadelegates;

import be.yelido.frameworktest.objects.Order;
import be.yelido.frameworktest.services.NormalShop;
import be.yelido.frameworktest.services.VIPShop;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class NormalShopTask implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(NormalShopTask.class);

    @Autowired
    private NormalShop normalShop;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("NormalShop execution");

        Order order = new Order(delegateExecution.getVariable("clientName").toString(),
                delegateExecution.getVariable("clientType").toString(),
                delegateExecution.getVariable("product").toString(),
                (Integer)delegateExecution.getVariable("amount"));
        normalShop.processOrder(order);

        delegateExecution.setVariableLocal("price", order.getPrice());
    }
}
