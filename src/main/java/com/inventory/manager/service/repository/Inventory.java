package com.inventory.manager.service.repository;

import com.inventory.manager.service.enums.InventoryState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class Inventory extends BasicEntity {

    @Column(name = "inventory_code", nullable = false)
    private String inventoryCode;

    @Column(name = "count", nullable = false)
    private Long count;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private InventoryState state;

}
