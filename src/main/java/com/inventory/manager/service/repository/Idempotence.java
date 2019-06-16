package com.inventory.manager.service.repository;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "idempotence", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = {"request_id", "request_name", "requested_by"})
        }
)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Idempotence {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
    @TableGenerator(name = "seqGen", allocationSize = 50)
    @Column(name = "id")
    private Long id;


    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "requested_by")
    private String requestedBy;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private Object responseBody;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

}
