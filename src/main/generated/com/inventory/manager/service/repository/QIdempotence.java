package com.inventory.manager.service.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdempotence is a Querydsl query type for Idempotence
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIdempotence extends EntityPathBase<Idempotence> {

    private static final long serialVersionUID = -969664302L;

    public static final QIdempotence idempotence = new QIdempotence("idempotence");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath requestedBy = createString("requestedBy");

    public final StringPath requestId = createString("requestId");

    public final StringPath requestName = createString("requestName");

    public final StringPath responseBody = createString("responseBody");

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QIdempotence(String variable) {
        super(Idempotence.class, forVariable(variable));
    }

    public QIdempotence(Path<? extends Idempotence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdempotence(PathMetadata metadata) {
        super(Idempotence.class, metadata);
    }

}

