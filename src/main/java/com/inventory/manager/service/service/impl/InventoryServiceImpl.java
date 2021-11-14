package com.inventory.manager.service.service.impl;

import com.inventory.manager.service.dao.InventoryDao;
import com.inventory.manager.service.datatypes.*;
import com.inventory.manager.service.datatypes.response.ClientResponse;
import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.enums.InventoryState;
import com.inventory.manager.service.repository.Inventory;
import com.inventory.manager.service.service.CacheService;
import com.inventory.manager.service.service.CustomLockEntityService;
import com.inventory.manager.service.service.InventoryService;
import com.inventory.manager.service.service.PublisherService;
import com.inventory.manager.service.utils.idempotency.Idempotent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    private CacheService inventoryCacheService;

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private CustomLockEntityService customLockEntityService;

    @Autowired
    private PublisherService publisherService;

    @Override
    @Idempotent
    public ClientResponse inwardInventory(InwardInventoryRequest request, IdempotencyKey idempotencyKey) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setCount(request.getCount());
        inventory.setInventoryCode(request.getInventoryCode());
        inventory.setState(InventoryState.LIVE);

        customLockEntityService.acquireLock(CustomeLockEntityName.INVENTORY, request.getInventoryCode());
        inventory = inventoryDao.createInventory(inventory);

        publisherService.publishSync(new SampleSyncEvent("SampleEventName", true));
        publisherService.publishSync(new AnotherSyncEvent("AnotherSyncEvent", true));
        publisherService.publishAsync(new SampleAsyncEvent("SampleAsyncEvent", true));

        return new ClientResponse(true, inventory);
    }

    @Override
    public ClientResponse getInventory(Long inventoryId) throws Exception {

        Inventory inventory = (Inventory) inventoryCacheService.get(inventoryId);

        if (Objects.isNull(inventory)) {
            inventory = inventoryDao.findInventory(inventoryId);

            inventoryCacheService.put(inventoryId, inventory);
        }

        return new ClientResponse(true, inventory);
    }
}
