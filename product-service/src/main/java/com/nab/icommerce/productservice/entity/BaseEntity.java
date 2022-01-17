package com.nab.icommerce.productservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@EntityListeners({AuditingEntityListener.class})
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Column(name = "created_time")
    @CreatedDate
    public Date createdTime;

    @Column(name = "updated_time")
    @LastModifiedDate
    public Date updatedTime;
}
