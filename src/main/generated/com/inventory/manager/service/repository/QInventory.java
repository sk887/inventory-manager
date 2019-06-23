package com.inventory.manager.service.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInventory is a Querydsl query type for Inventory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInventory extends EntityPathBase<Inventory> {

    private static final long serialVersionUID = -2080286415L;

    public static final QInventory inventory = new QInventory("inventory");

    public final QBasicEntity _super = new QBasicEntity(this);

    public final NumberPath<Long> count = createNumber("count", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath inventoryCode = createString("inventoryCode");

    public final EnumPath<com.inventory.manager.service.enums.InventoryState> state = createEnum("state", com.inventory.manager.service.enums.InventoryState.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QInventory(String variable) {
        super(Inventory.class, forVariable(variable));
    }

    public QInventory(Path<? extends Inventory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInventory(PathMetadata metadata) {
        super(Inventory.class, metadata);
    }

}

