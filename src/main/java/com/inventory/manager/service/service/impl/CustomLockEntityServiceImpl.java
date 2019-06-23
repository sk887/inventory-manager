package com.inventory.manager.service.service.impl;

import com.inventory.manager.service.dao.CustomLockEntityDao;
import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.repository.CustomLockEntity;
import com.inventory.manager.service.service.CustomLockEntityService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component("CustomLockEntityService")
public class CustomLockEntityServiceImpl implements CustomLockEntityService {

    @Autowired
    private CustomLockEntityDao customLockEntityRepository;

    private CustomLockEntity createLockEntry(CustomeLockEntityName entityName, String entityId) {
        CustomLockEntity customLockEntity = customLockEntityRepository.findByEntityNameAndEntityId(entityName, entityId);
        if (customLockEntity == null) {
            customLockEntity = new CustomLockEntity();
            customLockEntity.setEntityId(entityId);
            customLockEntity.setEntityName(entityName);
            customLockEntity.setCreatedAt(new Date());
            try {
                customLockEntityRepository.save(customLockEntity);
            } catch (Exception e) {
                throw e;
            }

            log.info("Lock Entry created for entityName " + entityName + " entityId " + entityId);
        }
        return customLockEntity;
    }


    private List<CustomLockEntity> createLockEntry(CustomeLockEntityName entityName, List<String> entityIds) {
        List<CustomLockEntity> customLockEntities = customLockEntityRepository.findByEntityNameAndEntityIdIn(entityName, entityIds);
        Map<String, CustomLockEntity> customLockEntityMap = customLockEntities.stream().collect(Collectors.toMap(CustomLockEntity::getEntityId, cl -> cl));

        List<CustomLockEntity> toCreate = new ArrayList<>();
        for (String entity : entityIds) {
            if (!customLockEntityMap.containsKey(entity)) {
                CustomLockEntity entry = new CustomLockEntity();
                entry.setEntityId(entity);
                entry.setEntityName(entityName);
                entry.setCreatedAt(new Date());
                toCreate.add(entry);
            }
        }

        try {
            customLockEntityRepository.save(toCreate);
        } catch (Exception e) {
            throw e;
        }

        List<CustomLockEntity> totalEntries = new ArrayList<>(customLockEntityMap.values());
        totalEntries.addAll(toCreate);
        return totalEntries;
    }

    @Override
    @Transactional
    public void acquireLock(CustomeLockEntityName entityName, String entityId) throws Exception {
        try {
            log.info("Attempting to acquire Lock for entityName " + entityName + " entityId " + entityId);
            long startTime = System.currentTimeMillis();
            CustomLockEntity customLockEntity = customLockEntityRepository.findByEntityNameAndEntityId(entityName, entityId);
            if (customLockEntity == null) {
                customLockEntity = createLockEntry(entityName, entityId);
            }

            customLockEntity = customLockEntityRepository.findByEntityNameAndEntityId(entityName, entityId, LockModeType.PESSIMISTIC_WRITE);
            long endTime = System.currentTimeMillis();
            log.info("Lock acquired for entityName " + entityName + " entityId " + entityId + " in " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void acquireLocks(CustomeLockEntityName entityName, List<String> entityIds) throws Exception {
        try {
            log.info("Attempting to acquire Lock for entityName " + entityName + " entityIds " + entityIds);
            long startTime = System.currentTimeMillis();
            List<CustomLockEntity> customLockEntities = customLockEntityRepository.findByEntityNameAndEntityIdIn(entityName, entityIds);
            if (customLockEntities == null || customLockEntities.size() != entityIds.size()) {
                customLockEntities = createLockEntry(entityName, entityIds);
            }

            customLockEntities = customLockEntityRepository.findByEntityNameAndEntityIdIn(entityName, entityIds, LockModeType.PESSIMISTIC_WRITE);
            long endTime = System.currentTimeMillis();
            log.info("Lock acquired for entityName " + entityName + " entityIds " + entityIds + " in " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            throw e;
        }
    }
}
