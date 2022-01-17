package com.nab.icommerce.shoppingcartservice.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "created_time")
    @CreatedDate
    public Date createdTime;

    @Column(name = "updated_time")
    @LastModifiedDate
    public Date updatedTime;
}
