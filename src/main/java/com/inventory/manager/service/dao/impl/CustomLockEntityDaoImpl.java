package com.inventory.manager.service.dao.impl;

import com.inventory.manager.service.dao.CustomLockEntityDaoCustom;
import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.repository.CustomLockEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
public class CustomLockEntityDaoImpl implements CustomLockEntityDaoCustom {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Value("${lockConfig.lockTimeOut:10000}")
    private Integer lockTimeOut;

    @Override
    public CustomLockEntity findByEntityNameAndEntityId(CustomeLockEntityName entityName, String entityId, LockModeType lockMode) {
        return null;
    }

    @Override
    public List<CustomLockEntity> findByEntityNameAndEntityIdIn(CustomeLockEntityName entityName, List<String> entityId, LockModeType lockMode) {
        return null;
    }
}
