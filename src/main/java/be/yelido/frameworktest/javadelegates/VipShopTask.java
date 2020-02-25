package be.yelido.frameworktest.javadelegates;

import be.yelido.frameworktest.objects.BillingInfo;
import be.yelido.frameworktest.objects.Order;
import be.yelido.frameworktest.services.PaymentService;
import be.yelido.frameworktest.services.VIPShop;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VipShopTask implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(VipShopTask.class);

    @Autowired
    private VIPShop vipShop;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("VipShopTask execution");

        try {
            Object a = delegateExecution.getVariable("amount");
            Order order = new Order(delegateExecution.getVariable("clientName").toString(),
                    delegateExecution.getVariable("clientType").toString(),
                    delegateExecution.getVariable("product").toString(),
                    (Integer)delegateExecution.getVariable("amount"));
            vipShop.processOrder(order);

            delegateExecution.setVariableLocal("price", order.getPrice());
        }catch (Exception e){
            throw new BpmnError("Error");
        }

    }
}
