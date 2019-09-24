package com.inventory.manager.service.service;

public interface CacheService<K, V> {

    V get(K k);

    void put(K key, V value);
}
