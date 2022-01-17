package com.nab.icommerce.purchaseorderservice.repository;

import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, Long> {
    List<PurchaseOrderDetail> findAll();
    List<PurchaseOrderDetail> findByPurchaseOrderId(Long purchaseOrderId);
}
