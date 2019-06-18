package com.inventory.manager.service.service;

import com.inventory.manager.service.datatypes.IdempotencyKey;
import com.inventory.manager.service.datatypes.InwardInventoryRequest;
import com.inventory.manager.service.datatypes.response.ClientResponse;
import com.inventory.manager.service.repository.Inventory;

public interface InventoryService {
    ClientResponse inwardInventory(InwardInventoryRequest request, IdempotencyKey idempotencyKey) throws Exception;
}
