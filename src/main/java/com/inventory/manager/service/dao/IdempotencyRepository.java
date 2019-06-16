package com.inventory.manager.service.dao;

import com.inventory.manager.service.repository.Idempotence;
import org.springframework.data.repository.CrudRepository;

public interface IdempotencyRepository extends CrudRepository<Idempotence, Long> {

    Idempotence findByRequestIdAndRequestNameAndRequestedBy(String requestId, String requestName,
                                                            String requestedBy) throws Exception;
}
