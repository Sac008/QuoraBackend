package com.project.Quora.user;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(nullable = false , updatable = false)
    private Date createdOn;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date updatedOn;

}
