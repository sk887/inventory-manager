package com.inventory.manager.service.service.impl;

import com.google.common.eventbus.EventBus;
import com.inventory.manager.service.datatypes.AsyncEvent;
import com.inventory.manager.service.datatypes.SyncEvent;
import com.inventory.manager.service.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DefaultPublisherService implements PublisherService {

    @Autowired
    @Qualifier(value = "syncEventBus")
    private EventBus syncEventBus;


    @Autowired
    @Qualifier(value = "asyncEventBus")
    private EventBus asyncEventBus;


    @Override
    public void publishSync(SyncEvent event) {
        syncEventBus.post(event);
    }

    @Override
    public void publishAsync(AsyncEvent event) {
        asyncEventBus.post(event);
    }
}
