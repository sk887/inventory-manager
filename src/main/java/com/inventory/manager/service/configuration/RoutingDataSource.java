package com.inventory.manager.service.configuration;

import com.inventory.manager.service.util.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("db look up key " + DbContextHolder.getDbType());
        return DbContextHolder.getDbType();
    }
}
