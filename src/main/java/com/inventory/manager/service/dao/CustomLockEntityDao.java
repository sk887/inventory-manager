package com.inventory.manager.service.dao;

import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.repository.CustomLockEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.List;

public interface CustomLockEntityDao extends CrudRepository<CustomLockEntity, Long>, CustomLockEntityDaoCustom {
    CustomLockEntity findByEntityNameAndEntityId(CustomeLockEntityName entityName, String entityId);

    List<CustomLockEntity> findByEntityNameAndEntityIdIn(CustomeLockEntityName entityName, List<String> entityId);
}
