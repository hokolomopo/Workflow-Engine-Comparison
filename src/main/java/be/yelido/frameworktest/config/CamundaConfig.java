package be.yelido.frameworktest.config;

import be.yelido.frameworktest.javadelegates.*;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CamundaConfig {


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

}
