package com.inventory.manager.service.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomLockEntity is a Querydsl query type for CustomLockEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomLockEntity extends EntityPathBase<CustomLockEntity> {

    private static final long serialVersionUID = 1354206666L;

    public static final QCustomLockEntity customLockEntity = new QCustomLockEntity("customLockEntity");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath entityId = createString("entityId");

    public final EnumPath<com.inventory.manager.service.enums.CustomeLockEntityName> entityName = createEnum("entityName", com.inventory.manager.service.enums.CustomeLockEntityName.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCustomLockEntity(String variable) {
        super(CustomLockEntity.class, forVariable(variable));
    }

    public QCustomLockEntity(Path<? extends CustomLockEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomLockEntity(PathMetadata metadata) {
        super(CustomLockEntity.class, metadata);
    }

}

