package be.yelido.frameworktest.config;

import be.yelido.frameworktest.javadelegates.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
