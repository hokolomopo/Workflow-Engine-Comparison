package be.yelido.frameworktest.config;

import be.yelido.frameworktest.JBPM.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBMPConfig {

//    @Bean
//    public IdentityProvider identityProvider(){
//        return new SpringSecurityIdentityProvider();
//    }

    @Bean
    public MyHandler workHandler(){
        return new MyHandler();
    }
}
