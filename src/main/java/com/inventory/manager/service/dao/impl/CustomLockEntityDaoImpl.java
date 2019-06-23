package com.inventory.manager.service.dao.impl;

import com.inventory.manager.service.dao.CustomLockEntityDaoCustom;
import com.inventory.manager.service.enums.CustomeLockEntityName;
import com.inventory.manager.service.repository.CustomLockEntity;
import com.inventory.manager.service.repository.QCustomLockEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
public class CustomLockEntityDaoImpl implements CustomLockEntityDaoCustom {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    QCustomLockEntity qCustomLockEntity = QCustomLockEntity.customLockEntity;

    @Value("${lockConfig.lockTimeOut:10000}")
    private Integer lockTimeOut;

    @Override
    public CustomLockEntity findByEntityNameAndEntityId(CustomeLockEntityName entityName, String entityId, LockModeType lockMode) {
        BooleanBuilder builder = getBuilder(entityName, entityId);
        if (builder.getValue() != null) {
            JPAQuery jpaQuery = getJpaQuery(builder, lockMode);
            List<CustomLockEntity> entities = jpaQuery.select(qCustomLockEntity).fetch();
            if (CollectionUtils.isEmpty(entities)) {
                return null;
            }
            return entities.get(0);
        }
        return null;
    }

    @Override
    public List<CustomLockEntity> findByEntityNameAndEntityIdIn(CustomeLockEntityName entityName, List<String> entityIds, LockModeType lockMode) {
        if (CollectionUtils.isEmpty(entityIds)) {
            return null;
        }
        BooleanBuilder builder = getBuilder(entityName, entityIds);
        if (builder.getValue() != null) {
            JPAQuery jpaQuery = getJpaQuery(builder, lockMode);
            List<CustomLockEntity> entities = jpaQuery.select(qCustomLockEntity).fetch();
            if (CollectionUtils.isEmpty(entities)) {
                return null;
            }
            return entities;
        }
        return null;
    }

    private JPAQuery getJpaQuery(BooleanBuilder builder, LockModeType lockMode) {
        JPAQuery jpaQuery = new JPAQueryFactory(entityManager).from(qCustomLockEntity)
                .where(builder);
        if (lockMode != null) {
            jpaQuery.setLockMode(lockMode);
            jpaQuery.setHint("javax.persistence.query.timeout", lockTimeOut);
        }

        return jpaQuery;
    }

    private BooleanBuilder getBuilder(CustomeLockEntityName entityName, String entityId) {
        BooleanBuilder builder = new BooleanBuilder();
        if (entityName != null) {
            builder.and(qCustomLockEntity.entityName.eq(entityName));
        }
        if (entityId != null) {
            builder.and(qCustomLockEntity.entityId.eq(entityId));
        }

        log.info("Predicate is : " + builder.getValue());
        return builder;
    }

    private BooleanBuilder getBuilder(CustomeLockEntityName entityName, List<String> entityIds) {
        BooleanBuilder builder = new BooleanBuilder();
        if (entityName != null) {
            builder.and(qCustomLockEntity.entityName.eq(entityName));
        }
        if (!CollectionUtils.isEmpty(entityIds)) {
            builder.and(qCustomLockEntity.entityId.in(entityIds));
        }

        log.info("Predicate is : " + builder.getValue());
        return builder;
    }

}
