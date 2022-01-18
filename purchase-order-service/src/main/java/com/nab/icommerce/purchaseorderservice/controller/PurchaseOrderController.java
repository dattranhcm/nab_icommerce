package com.nab.icommerce.purchaseorderservice.controller;

import com.nab.icommerce.purchaseorderservice.model.PurchaseOrderRequest;
import com.nab.icommerce.purchaseorderservice.model.PurchaseOrderResponse;
import com.nab.icommerce.purchaseorderservice.model.ShoppingCartItem;
import com.nab.icommerce.purchaseorderservice.service.PurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/purchase-order")
public class PurchaseOrderController {

    private PurchaseOrderService purchaseOrderService;
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }



    @GetMapping(value = "")
    @ResponseBody
    public PurchaseOrderResponse getOrderOfCustomer(@Nullable @RequestParam(value = "customerId") Long customerId) {
        return purchaseOrderService.getListOrder(customerId);
    }

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PurchaseOrderResponse updateOrder(@RequestBody PurchaseOrderRequest request) {
        return purchaseOrderService.updateOrderInfo(request);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PurchaseOrderResponse createBillableOrder(@RequestBody List<ShoppingCartItem> request) {
        return purchaseOrderService.createAnBillableOrder(request);
    }
}
