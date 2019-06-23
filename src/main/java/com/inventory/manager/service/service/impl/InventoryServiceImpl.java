package com.inventory.manager.service.service.impl;

import com.inventory.manager.service.dao.InventoryDao;
import com.inventory.manager.service.datatypes.IdempotencyKey;
import com.inventory.manager.service.datatypes.InwardInventoryRequest;
import com.inventory.manager.service.datatypes.response.ClientResponse;
import com.inventory.manager.service.enums.InventoryState;
import com.inventory.manager.service.repository.Inventory;
import com.inventory.manager.service.service.InventoryService;
import com.inventory.manager.service.utils.idempotency.Idempotent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;


    @Override
    @Idempotent
    public ClientResponse inwardInventory(InwardInventoryRequest request, IdempotencyKey idempotencyKey) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setCount(request.getCount());
        inventory.setInventoryCode(request.getInventoryCode());
        inventory.setState(InventoryState.LIVE);

        inventory = inventoryDao.createInventory(inventory);

        return new ClientResponse(true, inventory);
    }

    @Override
    public ClientResponse getInventory(Long inventoryId) throws Exception {
        Inventory inventory = inventoryDao.findInventory(inventoryId);

        return new ClientResponse(true, inventory);
    }
}
