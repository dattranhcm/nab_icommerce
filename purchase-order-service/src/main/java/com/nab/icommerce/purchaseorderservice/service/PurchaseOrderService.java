package com.nab.icommerce.purchaseorderservice.service;

import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrder;
import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrderDetail;
import com.nab.icommerce.purchaseorderservice.feign.CustomerClient;
import com.nab.icommerce.purchaseorderservice.feign.ProductClient;
import com.nab.icommerce.purchaseorderservice.model.*;
import com.nab.icommerce.purchaseorderservice.repository.PurchaseOrderRepository;
import com.nab.icommerce.purchaseorderservice.utils.PurchaseOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PurchaseOrderService {

  //  @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

 //   @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CustomerClient customerClient;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                PurchaseOrderDetailService purchaseOrderDetailService) {
        this.purchaseOrderDetailService = purchaseOrderDetailService;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrderResponse createAnBillableOrder(List<ShoppingCartItem> shoppingCartItems) {
        Long customerId = shoppingCartItems.get(0).getCustomerId();
        Long orderId = createEmptyPurchaseOrder(customerId, "HCM");
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(orderId);
        PurchaseOrder purchaseOrder = purchaseOrderOptional.get();
        shoppingCartItems.forEach(shoppingCartItem -> {
            purchaseOrderDetailService.createAOrderDetail(purchaseOrder, shoppingCartItem.getProductId(), shoppingCartItem.getProductPrice());
        });
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService.findByPurchaseOrderId(orderId);


        List<PurchaseOrderResponse.OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = new BigDecimal(30000);
        if(Objects.nonNull(purchaseOrderDetails)
            && purchaseOrderDetails.size() > 0) {

            purchaseOrderDetails.forEach(orderDetail -> {
                ProductResponse productResponse = productClient.getProducts(null, null, null, orderDetail.getProductId());
                orderItems.add(PurchaseOrderResponse.OrderItem.builder()
                        .id(orderDetail.getId())
                        .productItem(productResponse.getData().get(0))
                        .amount(orderDetail.getProductPrice()).build());
            });
        }

        CustomerResponse customerResponse = customerClient.getCustomer(customerId, null, null);

        return PurchaseOrderResponse.builder()
                .data(orderItems)
                .orderCode(purchaseOrder.getOrderCode())
                .totalAmount(totalAmount)
                .orderCode(purchaseOrder.getOrderCode())
                .shipAddress(purchaseOrder.getShipAddress())
                .customerInfo(PurchaseOrderResponse.CustomerInfo.builder()
                .firstName(customerResponse.getData().get(0).getFirstName())
                .lastName(customerResponse.getData().get(0).getLastName())
                        .address(customerResponse.getData().get(0).getAddress())
                        .mobile(customerResponse.getData().get(0).getMobile())
                        .build()
                )
                .status(purchaseOrder.getStatus())
                .build();
    }

    public PurchaseOrderResponse getListOrder(Long customerId) {
        List<PurchaseOrder> purchaseOrderEntities = purchaseOrderRepository.findByCustomerId(customerId);
        List<PurchaseOrderResponse.OrderItem> purchaseOrders = new ArrayList<>();
        purchaseOrderEntities.forEach(purchaseOrder ->
            purchaseOrders.add(PurchaseOrderResponse.OrderItem.builder()
            .id(purchaseOrder.getId())
            .build()
            ));
        return PurchaseOrderResponse.builder().data(purchaseOrders).build();
    }

    public PurchaseOrderResponse updateOrderInfo(PurchaseOrderRequest request) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByOrderCode(request.getOrderCode());
        if(Objects.nonNull(purchaseOrder)) {
            purchaseOrder.setShipAddress(request.getShipAddress());
            purchaseOrder.setStatus(request.getStatus());
            purchaseOrder.setUpdatedTime(new Date());
            purchaseOrderRepository.save(purchaseOrder);
        }
        return PurchaseOrderResponse.builder().build();
    }

    private Long createEmptyPurchaseOrder(Long customerId, String customerAddress ) {
        return purchaseOrderRepository.saveAndFlush(PurchaseOrder.builder()
                .shipAddress(customerAddress)
                .totalAmount(new BigDecimal(0))
                .customerId(customerId)
                .orderCode("")
                .status(PurchaseOrderStatus.AVAILABLE.toString())
                .createdTime(new Date())
                .updatedTime(new Date())
                .build()).getId();
    }
}
