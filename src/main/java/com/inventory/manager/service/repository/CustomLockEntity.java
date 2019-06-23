package com.inventory.manager.service.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inventory.manager.service.enums.CustomeLockEntityName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "custom_lock",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = {"entity_name", "entity_id"})}
)
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomLockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "entity_name")
    private CustomeLockEntityName entityName;

    @NotNull
    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;
}
