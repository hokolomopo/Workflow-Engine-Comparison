package be.yelido.frameworktest.config;

import be.yelido.frameworktest.javadelegates.*;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ActivitiConfig {

    @Bean
    public PaymentServiceTask paymentServiceTask(){
        return new PaymentServiceTask();
    }

    @Bean
    public PaymentAcceptedTask paymentAcceptedTask(){
        return new PaymentAcceptedTask();
    }

    @Bean
    public PaymentRefusedTask paymentRefusedTask(){
        return new PaymentRefusedTask();
    }

    @Bean
    public VipShopTask vipShopTask(){
        return new VipShopTask();
    }

    @Bean
    public NormalShopTask normalShopTask(){
        return new NormalShopTask();
    }


    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            config.setDeploymentResources(
                    resourcePatternResolver.getResources("classpath*:processes/**/*.bpmn"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDataSource(dataSource);
        config.setTransactionManager(platformTransactionManager);
        config.setDatabaseSchemaUpdate("true");
        config.setHistory("audit");

        // IMPORTANT. If not there, no JobExecutor is started and the processes are not run
        config.setAsyncExecutorActivate(true);
        return config;
    }


}
