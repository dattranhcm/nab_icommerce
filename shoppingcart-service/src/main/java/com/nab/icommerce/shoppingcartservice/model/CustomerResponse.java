package com.nab.icommerce.shoppingcartservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private List<CustomerItem> data;
    private int currentPage;
    private long totalItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomerItem {
        private Long id;
        private String userName;
        private String hashPassword;
        private String firstName;
        private String lastName;
        private String email;
        private String mobile;
        private String address;
        private String status;
    }
}
