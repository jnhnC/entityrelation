package com.example.entityrelation.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    //    @PrePersist
//    public void prePersist(){
//        LocalDateTime now = LocalDateTime.now();
//        createdDate = now;
//        updatedDate = now;
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        updatedDate = LocalDateTime.now();
//    }


}
