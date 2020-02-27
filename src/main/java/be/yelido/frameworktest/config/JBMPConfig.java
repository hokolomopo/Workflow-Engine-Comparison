package be.yelido.frameworktest.config;

import be.yelido.frameworktest.SpringSecurityIdentityProvider;
import org.kie.internal.identity.IdentityProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JBMPConfig {

    @Bean
    public IdentityProvider identityProvider(){
        return new SpringSecurityIdentityProvider();
    }
}
