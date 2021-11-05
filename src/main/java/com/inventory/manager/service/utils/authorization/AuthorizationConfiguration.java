package com.inventory.manager.service.utils.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AuthorizationConfiguration {

    @Bean
    public AuthorizationAspect getAuthorizationAspect() {
        return new AuthorizationAspect();
    }
}
