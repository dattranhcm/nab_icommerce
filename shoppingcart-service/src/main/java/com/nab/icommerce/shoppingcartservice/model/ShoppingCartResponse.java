package com.nab.icommerce.shoppingcartservice.model;

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
public class ShoppingCartResponse {

    private CustomerInfo customerInfo;
    private Long customerId;
    private List<ShoppingCartItem> data;
    private int currentPage;
    private long totalItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ShoppingCartItem {
        private Long id;
        private String productName;
        private String productCode;
        private BigDecimal productPrice;
        private String status;
        private Date createdTime;
        private Date updatedTime;
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
