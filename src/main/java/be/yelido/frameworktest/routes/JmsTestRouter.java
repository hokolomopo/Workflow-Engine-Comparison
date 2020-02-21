package be.yelido.frameworktest.routes;

import be.yelido.frameworktest.objects.BillingInfo;
import be.yelido.frameworktest.objects.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JmsTestRouter extends RouteBuilder {

    @Autowired
    private RuntimeService runtimeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure() throws Exception {
        System.out.println("Configuring route");

        from("jms:{{input.queue}}")
                .log(LoggingLevel.DEBUG, log, "New message in input queue")
                .process(this::startProcess)
                .choice()
                    .when().jsonpath(("$[?(@.clientType =~ /.*VIP/i)]"))
                        .to("jms:{{shop.queue.vip}}").log(LoggingLevel.DEBUG, log, "Message send to vip shop")
                    .otherwise().to("jms:{{shop.queue.normal}}").log(LoggingLevel.DEBUG, log, "Message send to normal shop")
                .end();

        from("jms:{{shop.queue.output}}")
                .log(LoggingLevel.DEBUG, log, "New message received on shop output queue")
                .process(this::endShop)
                .to("jms:{{payment.queue}}")
                .log(LoggingLevel.DEBUG, log, "Message is successfully sent to the paymeny queue")
                .end();

//        from("jms:{{payment.queue.output}}")
//                .log(LoggingLevel.DEBUG, log, "New message in payment output queue")
//                .process(exchange -> stepProcessInstance(exchange, "PayServiceMessage", "clientName"))
//                .choice()
//                    .when().jsonpath("amount == 10")
//                        .to("jms:{{payment.queue.accepted}}")
//                        .log(LoggingLevel.DEBUG, log, "Message send to accepted payment")
//                    .otherwise()
//                        .to("jms:{{payment.queue.refused}}")
//                        .log(LoggingLevel.DEBUG, log, "Message send to refused payment")
//                .end();

    }

    private void startProcess(Exchange exchange) throws Exception{
        String body = exchange.getMessage().getBody().toString();
        Map<String, Object> variables = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {});

        runtimeService.startProcessInstanceByKey("SimpleShopTest", variables);
    }

    private void stepProcessInstance(Exchange exchange, String messageName, String idName) throws Exception{
        String body = exchange.getMessage().getBody().toString();
        Map<String, Object> variables = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {});

        MessageCorrelationResult result = runtimeService.createMessageCorrelation(messageName)
                .processInstanceVariableEquals(idName, variables.get(idName))
                .setVariables(variables)
                .correlateWithResult();
    }

    private void endShop(Exchange exchange) throws Exception{
        String body = exchange.getMessage().getBody().toString();
        Map<String, Object> variables = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {});

        MessageCorrelationResult result = runtimeService.createMessageCorrelation("EndShopMessage")
                .processInstanceVariableEquals("clientName", variables.get("clientName"))
                .setVariable("price", variables.get("price"))
                .correlateWithResult();


//            runtimeService.createIncident("Erreur i guess", subscription.getExecutionId(), "?", "Y'a une erreur");
//            ProcessInstance pi = runtimeService.createProcessInstanceQuery()
//                    .processDefinitionKey("SimpleShopTest")
//                    .variableValueEquals("clientName", info.getClientName())
//                    .singleResult();;
//
//            EventSubscription subscription = runtimeService.createEventSubscriptionQuery()
//                    .processInstanceId(pi.getId()).eventType("message").singleResult();
//
//            runtimeService.messageEventReceived(subscription.getEventName(), subscription.getExecutionId(), variables);

    }


}