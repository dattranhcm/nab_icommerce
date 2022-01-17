package com.nab.icommerce.purchaseorderservice.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartItem {
    private Long customerId;
    private Long productId;
    private BigDecimal productPrice;
    private String status;
}
