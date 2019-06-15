package com.inventory.manager.service.service;

import com.inventory.manager.service.datatypes.InwardInventoryRequest;

public interface InventoryService {
    void inwardInventory(InwardInventoryRequest request) throws Exception;
}
