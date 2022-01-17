package com.nab.icommerce.customerservice.service;

import com.nab.icommerce.customerservice.entity.Customer;
import com.nab.icommerce.customerservice.model.CustomerRequest;
import com.nab.icommerce.customerservice.model.CustomerResponse;
import com.nab.icommerce.customerservice.repository.CustomerRepository;
import com.nab.icommerce.customerservice.utils.CustomerStatus;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse getCustomers(Long id, String email, String mobile) {
        List<Customer> customerEntities;
        if(Objects.isNull(id) && Objects.isNull(email) && Objects.isNull(mobile)) {
            customerEntities = customerRepository.findAll();
        } else {
            customerEntities = customerRepository.findByIdOrEmailOrMobile(id, email, mobile);
        }

        List<CustomerResponse.CustomerItem> productItems = new ArrayList<>();
        customerEntities.forEach(customer -> {
            productItems.add(CustomerResponse.CustomerItem.builder()
            .email(customer
                    .getEmail())
                    .lastName(customer.getLastName())
                    .address(customer.getAddress())
                    .id(customer.getId())
                    .userName(customer.getUserName())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .createdTime(customer.getCreatedTime())
                    .updatedTime(customer.getUpdatedTime())
            .build()
            );
        });
        return CustomerResponse.builder().data(productItems).build();
    }

    @SneakyThrows
    public CustomerResponse.CustomerItem registNewCustomer(CustomerRequest request) {
        List<Customer> customers = customerRepository.findByIdOrEmailOrMobile(null, request.getEmail(), request.getMobile());
        if(customers.size() == 0) {
            Customer customer = customerRepository.save(Customer.builder()
                    .email(request.getEmail())
                    .lastName(request.getLastName())
                    .address(request.getAddress())
                    .userName(request.getUserName())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .status(CustomerStatus.AVAILABLE.toString())
                    .createdTime(new Date())
                    .updatedTime(new Date())
                    .build());

            return CustomerResponse.CustomerItem.builder()
                    .userName(customer.getUserName())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .address(customer.getAddress())
                    .mobile(customer.getMobile())
                    .status(customer.getStatus())
                    .createdTime(customer.getCreatedTime())
                    .updatedTime(customer.getUpdatedTime())
                    .build();
        } else {
            throw new Exception();
        }
    }
}
