package com.nab.icommerce.productservice.repository;

import com.nab.icommerce.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameOrProductCodeOrStatus(String productName, String productCode, String status);
}
