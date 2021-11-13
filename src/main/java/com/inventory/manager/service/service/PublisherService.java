package com.inventory.manager.service.service;

import com.inventory.manager.service.datatypes.AsyncEvent;
import com.inventory.manager.service.datatypes.SyncEvent;

public interface PublisherService {
    void publishSync(SyncEvent event);

    void publishAsync(AsyncEvent event);
}
