package com.nab.icommerce.purchaseorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderResponse {

    private CustomerInfo customerInfo;
    private List<OrderItem> data;
    private BigDecimal totalAmount;
    private String shipAddress;
    private String orderCode;
    private String status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderItem {
        private Long id;
        private ProductResponse.ProductItem productItem;
        private BigDecimal amount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomerInfo {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String mobile;
        private String address;
    }
}
