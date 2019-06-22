package com.inventory.manager.service.utils.readOnlyConnection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ReadOnlyConfiguration {
    @Bean
    public ReadOnlyAspect getReadOnly() {
        return new ReadOnlyAspect();
    }
}
