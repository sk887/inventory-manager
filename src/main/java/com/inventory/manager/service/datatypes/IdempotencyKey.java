package com.inventory.manager.service.datatypes;

import lombok.Data;

@Data
public class IdempotencyKey {
    private String requestId;
    private String requestedBy;

    public IdempotencyKey(String requestId, String requestedBy) {
        this.requestId = requestId;
        this.requestedBy = requestedBy;
    }
}
