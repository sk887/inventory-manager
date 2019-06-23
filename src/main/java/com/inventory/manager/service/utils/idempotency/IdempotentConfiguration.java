package com.inventory.manager.service.utils.idempotency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class IdempotentConfiguration {
    @Bean
    public IdempotenceAspect getIdempotentAspect() {
        return new IdempotenceAspect();
    }
}
