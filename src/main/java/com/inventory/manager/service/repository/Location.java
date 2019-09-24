package com.inventory.manager.service.repository;

import com.inventory.manager.service.enums.InventoryState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location extends BasicEntity {

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "code", nullable = false)
    private Long locationCode;

    @Column(name = "address line 1", nullable = false)
    private String locationAddressline1;

    @Column(name = "address line 2", nullable = false)
    private String locationAddressline2;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @Column(name = "city", nullable = false)
    private String city;
}