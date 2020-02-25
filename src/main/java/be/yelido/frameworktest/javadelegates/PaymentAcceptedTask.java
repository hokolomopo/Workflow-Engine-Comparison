package be.yelido.frameworktest.javadelegates;

import be.yelido.frameworktest.services.AcceptedPaymentService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class PaymentAcceptedTask implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(PaymentAcceptedTask.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("PaymentAcceptedTask");
        throw new IOException("Intended error for testing purposes");
    }
}
