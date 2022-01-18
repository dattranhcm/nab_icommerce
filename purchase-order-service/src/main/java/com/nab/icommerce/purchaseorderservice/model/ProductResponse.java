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
public class ProductResponse {

    private List<ProductItem> data;
    private int currentPage;
    private long totalItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductItem {
        private Long id;
        private String productName;
        private String productCode;
        private BigDecimal price;
        private String status;
    }
}
