package com.nab.icommerce.purchaseorderservice.service;

import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrder;
import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrderDetail;
import com.nab.icommerce.purchaseorderservice.repository.PurchaseOrderDetailRepository;
import com.nab.icommerce.purchaseorderservice.utils.PurchaseOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderDetailService {

    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    public PurchaseOrderDetailService(PurchaseOrderDetailRepository purchaseOrderDetailRepository) {
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
    }

    public PurchaseOrderDetail createAOrderDetail(PurchaseOrder purchaseOrder, Long productId, BigDecimal productPrice) {
       return purchaseOrderDetailRepository.save(PurchaseOrderDetail.builder()
                .createdTime(new Date())
                .updatedTime(new Date())
                .purchaseOrder(purchaseOrder)
                .status(PurchaseOrderStatus.AVAILABLE.toString())
                .productId(productId)
                .productPrice(productPrice)
                .build());
    }

    public List<PurchaseOrderDetail> findByPurchaseOrderId(Long purchaseOrderId) {
        return purchaseOrderDetailRepository.findByPurchaseOrderId(purchaseOrderId);
    }
}
