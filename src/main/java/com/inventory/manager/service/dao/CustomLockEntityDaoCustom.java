package com.inventory.manager.service.dao;

import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.repository.CustomLockEntity;

import javax.persistence.LockModeType;
import java.util.List;

public interface CustomLockEntityDaoCustom {

    CustomLockEntity findByEntityNameAndEntityId(CustomeLockEntityName entityName, String entityId, LockModeType lockMode);

    List<CustomLockEntity> findByEntityNameAndEntityIdIn(CustomeLockEntityName entityName, List<String> entityId, LockModeType lockMode);
}