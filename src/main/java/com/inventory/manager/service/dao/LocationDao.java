package com.inventory.manager.service.dao;

import com.inventory.manager.service.repository.Location;

public interface LocationDao {
    Location create(Location location);

    void updateLocation(Location location);

    Location search(Long id);
}
