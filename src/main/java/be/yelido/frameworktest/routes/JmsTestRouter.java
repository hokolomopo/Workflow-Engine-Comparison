package be.yelido.frameworktest.routes;

import be.yelido.frameworktest.objects.BillingInfo;
import be.yelido.frameworktest.objects.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;

@Component
public class JmsTestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        System.out.println("Configuring route");
//
//        from("jms:{{input.queue}}")
//                .log(LoggingLevel.DEBUG, log, "New message in input queue")
//                .choice()
//                    .when().jsonpath(("$[?(@.clientType =~ /.*VIP/i)]"))
//                        .to("jms:{{shop.queue.vip}}").log(LoggingLevel.DEBUG, log, "Message send to vip shop")
//                    .otherwise().to("jms:{{shop.queue.normal}}").log(LoggingLevel.DEBUG, log, "Message send to normal shop")
//                .end();
//
//        from("jms:{{shop.queue.output}}")
//                .log(LoggingLevel.DEBUG, log, "New message received on shop output queue")
//                .to("jms:{{payment.queue}}")
//                .log(LoggingLevel.DEBUG, log, "Message is successfully sent to the paymeny queue")
//                .end();

    }
}