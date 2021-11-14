package com.inventory.manager.service.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.MoreExecutors;
import com.inventory.manager.service.decorator.ContextAwareExecutor;
import com.inventory.manager.service.service.impl.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class EventBusConfiguration {

    @Autowired
    private ConsumerService consumerService;

    @Bean(value = "syncEventBus")
    public EventBus syncEventBus() {
        EventBus eventBus = new EventBus("sync_event_bus");
        eventBus.register(consumerService);

        return eventBus;
    }


    @Bean(value = "asyncEventBus")
    public EventBus asyncEventBus() {
        EventBus eventBus = new AsyncEventBus("async_event_bus", new ContextAwareExecutor(new ThreadPoolExecutor(10,54,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50000))));
        eventBus.register(consumerService);

        return eventBus;
    }
}
