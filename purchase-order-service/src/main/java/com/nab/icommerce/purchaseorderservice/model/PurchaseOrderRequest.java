package com.nab.icommerce.purchaseorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderRequest {
    private Long customerId;
    private BigDecimal totalAmount;
    private String shipAddress;
    private String orderCode;
    private String status;
}
