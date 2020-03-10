package be.yelido.frameworktest.JBPM;

import be.yelido.frameworktest.routes.WebRouter;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("PaymentService")
public class PaymentServiceHandler implements WorkItemHandler {
    private Logger logger = LoggerFactory.getLogger(PaymentServiceHandler.class);

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

        logger.info("PaymentServiceHandler");

        manager.completeWorkItem(workItem.getId(), null);
    }


    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
    }
}
