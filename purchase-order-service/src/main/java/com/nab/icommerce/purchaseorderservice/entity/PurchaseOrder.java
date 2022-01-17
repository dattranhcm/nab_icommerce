package com.nab.icommerce.purchaseorderservice.entity;

import lombok.*;
import static org.hibernate.annotations.CascadeType.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "status")
    private String status;

    @Cascade({ALL, DELETE})
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchaseOrder")
    private Set<PurchaseOrderDetail> purchaseOrderDetails;

    @Column(name = "created_time")
    @CreatedDate
    public Date createdTime;

    @Column(name = "updated_time")
    @LastModifiedDate
    public Date updatedTime;
}
