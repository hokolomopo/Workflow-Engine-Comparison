package be.yelido.frameworktest;

import be.yelido.frameworktest.objects.Order;
import be.yelido.frameworktest.routes.JmsTestRouter;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
//@ImportResource(value= {"classpath:jbpm.xml",})
public class TestWorkflowEnginesApplication {

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(TestWorkflowEnginesApplication.class, args);

//		KieServices kService = KieServices.Factory.get();
//		KieContainer kContainer = kService.getKieClasspathContainer();
//		KieBase kbase = kContainer.getKieBase("id");
	}

}