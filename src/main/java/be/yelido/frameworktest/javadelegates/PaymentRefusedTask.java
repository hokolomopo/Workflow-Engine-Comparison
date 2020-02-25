package be.yelido.frameworktest.javadelegates;

import be.yelido.frameworktest.services.AcceptedPaymentService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentRefusedTask implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(PaymentRefusedTask.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("PaymentRefusedTask");

        //Do stuff from RefusedPaymentService
    }
}