package com.inventory.manager.service.dao;

import com.inventory.manager.service.repository.Inventory;

public interface InventoryDao {
    Inventory createInventory(Inventory inventory);

    Inventory updateInventory(Inventory inventory);

    Inventory findInventory(Long inventoryId);
}
