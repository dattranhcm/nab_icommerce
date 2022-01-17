package com.nab.icommerce.shoppingcartservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartRequest {
    private Long customerId;
    private Long productId;
    private BigDecimal price;
    private String status;
}
