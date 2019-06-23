package com.inventory.manager.service.dao.impl;

import com.inventory.manager.service.dao.InventoryDao;
import com.inventory.manager.service.repository.Inventory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class InventoryDaoImpl extends AbstractDao<Inventory, Long> implements InventoryDao {

    public InventoryDaoImpl(EntityManager entityManager) {
        super(entityManager, Inventory.class);

    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return insert(inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return update(inventory);
    }

    public Inventory findInventory(Long inventoryId) {
        return findById(inventoryId);
    }
}
