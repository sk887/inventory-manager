package com.inventory.manager.service.dao.impl;

import com.inventory.manager.service.dao.LocationDao;
import com.inventory.manager.service.repository.Inventory;
import com.inventory.manager.service.repository.Location;

import javax.persistence.EntityManager;

public class LocationDaoImpl extends AbstractDao<Location, Long> implements LocationDao {

    public LocationDaoImpl(EntityManager entityManager) {
        super(entityManager, Location.class);
    }

    @Override
    public Location create(Location location) {
        return insert(location);
    }

    @Override
    public void updateLocation(Location location) {
        update(location);
    }

    @Override
    public Location search(Long id) {
        return findById(id);
    }
}
