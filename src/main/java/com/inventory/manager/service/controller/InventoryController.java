package com.inventory.manager.service.controller;

import com.inventory.manager.service.datatypes.InwardInventoryRequest;
import com.codahale.metrics.annotation.Timed;
import com.inventory.manager.service.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/create")
    @Transactional
    @Timed
    public ResponseEntity inwardEvnetory(@RequestBody InwardInventoryRequest request) {
        try {
            inventoryService.inwardInventory(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }
}
