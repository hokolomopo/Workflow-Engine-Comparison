package be.yelido.frameworktest.javadelegates;

import be.yelido.frameworktest.objects.BillingInfo;
import be.yelido.frameworktest.services.PaymentService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceTask implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(PaymentServiceTask.class);

    @Autowired
    private PaymentService paymentService;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        log.info("PaymentServiceTask execution");
        BillingInfo info = new BillingInfo(delegateExecution.getVariable("clientName").toString(),
                (Integer)delegateExecution.getVariable("price"));

        paymentService.processBill(info);

        delegateExecution.setVariableLocal("accepted", info.isAccepted());
    }
}
