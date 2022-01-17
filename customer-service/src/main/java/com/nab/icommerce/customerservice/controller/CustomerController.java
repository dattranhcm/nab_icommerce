package com.nab.icommerce.customerservice.controller;

import com.nab.icommerce.customerservice.model.CustomerRequest;
import com.nab.icommerce.customerservice.model.CustomerResponse;
import com.nab.icommerce.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

@Slf4j
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerResponse.CustomerItem registNewCustomer(@RequestBody CustomerRequest request) {
        return customerService.registNewCustomer(request);
    }

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest request) {
        return CustomerResponse.builder().build();
    }

    @GetMapping(value = "")
    @ResponseBody
    public CustomerResponse getCustomer(@Nullable @RequestParam(value = "id") Long id,
                                        @Nullable @RequestParam(value = "email") String email,
                                        @Nullable @RequestParam(value = "mobile") String mobile) {
        return customerService.getCustomers(id, email, mobile);
    }

}
