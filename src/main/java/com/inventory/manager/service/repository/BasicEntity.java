package com.inventory.manager.service.repository;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BasicEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;


    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Temporal(TemporalType.TIMESTAMP) //Hibernate could store date without hours, minutes and seconds
    @Column(name = "created_at", nullable = false)
    @Getter @Setter
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @Getter @Setter
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        if(createdAt==null)
            createdAt = date;
        if(updatedAt==null)
            updatedAt = date;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (!(obj instanceof BasicEntity)){
            return false;
        }
        final BasicEntity other = (BasicEntity) obj;
        if (this.id != null && other.id != null) {
            if (this.getClass().equals(other.getClass()) && (this.id.equals(other.id))) {
                return true;
            }
        }
        return false;
    }

}
