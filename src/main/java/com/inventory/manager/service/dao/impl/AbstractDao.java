package com.inventory.manager.service.dao.impl;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<E, P> {

    @Getter
    @PersistenceContext
    private EntityManager entityManager;
    @Getter
    private Class<E> entityClass;

    public AbstractDao(EntityManager entityManager, Class<E> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public E findById(P id) {
        return entityManager.find(getEntityClass(), id);
    }

    protected E insert(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    protected List<E> insert(List<E> entities) {
        List<E> result = new ArrayList<E>();

        if (entities == null) {
            return result;
        }

        for (E entity : entities) {
            result.add(insert(entity));
        }

        return result;
    }

    protected E update(E entity) {
        entityManager.merge(entity);
        return entity;
    }

    protected List<E> update(List<E> entities) {
        List<E> result = new ArrayList<E>();

        if (entities == null) {
            return result;
        }

        for (E entity : entities) {
            result.add(update(entity));
        }

        return result;
    }

    protected List<E> findAll() {
        CriteriaQuery query = getCriteriaQuery();

        Root root = query.from(entityClass);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }


    protected CriteriaQuery<E> getCriteriaQuery() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        return builder.createQuery(entityClass);
    }

    protected List<E> findByIds(List<P> ids) {
        if (ids == null) {
            throw new RuntimeException("List of ids cannot be null");
        }
        if (ids.isEmpty()) {
            return new ArrayList<E>();
        }
        CriteriaQuery criteriaQuery = getCriteriaQuery();
        Metamodel m = getEntityManager().getMetamodel();
        EntityType<E> roadVehicleTypeEntityType_ = m.entity(getEntityClass());
        Root<E> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.where(root.get(roadVehicleTypeEntityType_.getId(Long.class)).in(ids));

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

}