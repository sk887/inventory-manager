package com.inventory.manager.service.service;

import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.repository.CustomLockEntity;

import java.util.List;

public interface CustomLockEntityService {
    
    void acquireLock(CustomeLockEntityName entityName, String entityId) throws Exception;

    void acquireLocks(CustomeLockEntityName entityName, List<String> entityId) throws Exception;
}
