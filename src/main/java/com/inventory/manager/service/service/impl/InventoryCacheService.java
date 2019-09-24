package com.inventory.manager.service.service.impl;

import com.inventory.manager.service.repository.Inventory;
import com.inventory.manager.service.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class InventoryCacheService implements CacheService<Long, Inventory> {

    private static final Object lock = new Object();
    private static final HashMap<Long, Inventory> cacheMap = new HashMap<>();


    @Override
    public Inventory get(Long inventoryId) {
        log.info("Getting value for key " + inventoryId);
        return cacheMap.getOrDefault(inventoryId, null);
    }

    @Override
    public void put(Long key, Inventory value) {
        cacheMap.put(key, value);
    }
}
