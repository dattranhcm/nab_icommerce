package com.nab.icommerce.customerservice.repository;

import com.nab.icommerce.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> findByIdOrEmailOrMobile(Long id, String email, String mobile);
    List<Customer> findByStatus(String status);
}
