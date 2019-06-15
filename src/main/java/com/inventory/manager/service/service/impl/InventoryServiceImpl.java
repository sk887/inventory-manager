package com.inventory.manager.service.service.impl;

import com.inventory.manager.service.dao.InventoryDao;
import com.inventory.manager.service.datatypes.InwardInventoryRequest;
import com.inventory.manager.service.enums.InventoryState;
import com.inventory.manager.service.repository.Inventory;
import com.inventory.manager.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;


    @Override
    public void inwardInventory(InwardInventoryRequest request) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setCount(request.getCount());
        inventory.setInventoryCode(request.getInventoryCode());
        inventory.setState(InventoryState.LIVE);

        inventoryDao.createInventory(inventory);
    }
}
