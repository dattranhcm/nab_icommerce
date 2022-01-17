package com.nab.icommerce.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String userName;
    private String hashPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;
}
