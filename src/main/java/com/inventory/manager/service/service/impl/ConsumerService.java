package com.inventory.manager.service.service.impl;

import com.google.common.eventbus.Subscribe;
import com.inventory.manager.service.datatypes.SampleAsyncEvent;
import com.inventory.manager.service.datatypes.SampleSyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerService {

    @Subscribe
    public void consumeSync(SampleSyncEvent event) {
        log.info("Sync event: " + event);
    }

    @Subscribe
    public void consumeAsync(SampleAsyncEvent event) throws InterruptedException {
        Thread.sleep(100);
        log.info("Async event: " + event);
    }
}
