package be.yelido.frameworktest;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

//Exclude to have access to the H2 console
@SpringBootApplication(exclude = org.kie.server.springboot.autoconfiguration.security.DefaultWebSecurityConfig.class)
@EnableJms
//@ImportResource(value= {"classpath:jbpm.xml",})
public class TestWorkflowEnginesApplication {

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(TestWorkflowEnginesApplication.class, args);
	}

	@Autowired
	private ProcessService processService;

	@Autowired
	private DeploymentService deploymentService;

	@Autowired
	private RuntimeDataService runtimeDataService;

	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
		@Override
		public void run(String...args) throws Exception {
			System.out.println("CommandLineRunner");
//			KieServices kService = KieServices.Factory.get();
//			kService.getResources().newClassPathResource("sample.bpmn");
//			KieContainer kContainer = kService.getKieClasspathContainer();
//			KieBase kieBase = kContainer.getKieBase();
//			KieSession session = kieBase.newKieSession();
//			ProcessInstance instance = session.startProcess("com.sample.bpmn.hello");

//			KModuleDeploymentUnit deploymentUnit = new KModuleDeploymentUnit("be.yelido", "hello-kie-server", "1.0");
//			deploymentService.deploy(deploymentUnit);
//			Long id = processService.startProcess(deploymentUnit.getIdentifier(), "hello");

//			KModuleDeploymentUnit deploymentUnit =
//					new KModuleDeploymentUnit("be.yelido", "test-things", "1.0");
//			deploymentService.deploy(deploymentUnit);

			KModuleDeploymentUnit deploymentUnit =
					new KModuleDeploymentUnit("be.yelido", "custom", "1.0");
			deploymentService.deploy(deploymentUnit);

//			Long id = processService.startProcess(deploymentUnit.getIdentifier(), "test");
//
//			ProcessInstance p = processService.getProcessInstance(id);
//
//			System.out.println(p != null);
//
//			ArrayList<ProcessInstanceDesc> a = new ArrayList<>(runtimeDataService.getProcessInstances(null));

		}
		}

	}