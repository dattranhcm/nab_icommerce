package com.nab.icommerce.shoppingcartservice.repository;

import com.nab.icommerce.shoppingcartservice.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAll();
    List<ShoppingCart> findByCustomerId(Long customerId);
}
