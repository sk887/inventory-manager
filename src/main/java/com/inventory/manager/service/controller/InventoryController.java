package com.inventory.manager.service.controller;

import com.codahale.metrics.annotation.Timed;
import com.inventory.manager.service.datatypes.IdempotencyKey;
import com.inventory.manager.service.datatypes.InwardInventoryRequest;
import com.inventory.manager.service.datatypes.response.ClientResponse;
import com.inventory.manager.service.service.InventoryService;
import com.inventory.manager.service.utils.authorization.Authorization;
import com.inventory.manager.service.utils.context.SampleContextHolder;
import com.inventory.manager.service.utils.readOnlyConnection.ReadOnlyConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/create")
    @Transactional
    @Timed
    @Authorization(value = "test")
    public ResponseEntity inwardInventory(
            @RequestHeader(value = "X_REQUEST_ID") String requestId,
            @RequestHeader(value = "X_REQUESTED_BY") String requestedBy,
            @RequestBody InwardInventoryRequest request) {

        ClientResponse clientResponse = null;
        try {
            IdempotencyKey idempotencyKey = new IdempotencyKey(requestId, requestedBy);

            clientResponse = inventoryService.inwardInventory(request, idempotencyKey);

        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientResponse);
    }

    @GetMapping
    @Transactional
    @Timed
    @ReadOnlyConnection
    public ResponseEntity getInventory(@RequestParam(value = "inventory_id") Long inventoryId) {
        ClientResponse clientResponse = null;

        try {
            clientResponse = inventoryService.getInventory(inventoryId);

        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientResponse);
    }
}