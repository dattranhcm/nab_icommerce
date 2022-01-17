package com.nab.icommerce.purchaseorderservice.repository;

import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrder;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findAll();
    List<PurchaseOrder> findByCustomerId(Long customerId);
    PurchaseOrder findByOrderCode(String orderCode);
    List<PurchaseOrder> findByStatus(String status);
}
