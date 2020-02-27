package be.yelido.frameworktest.javadelegates;

import be.yelido.frameworktest.services.AcceptedPaymentService;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class PaymentAcceptedTask implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(PaymentAcceptedTask.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("PaymentAcceptedTask");
//        throw new ActivitiException("Intended error for testing purposes");
    }
}
