package com.nab.icommerce.purchaseorderservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrder
import com.nab.icommerce.purchaseorderservice.entity.PurchaseOrderDetail
import com.nab.icommerce.purchaseorderservice.feign.ProductClient
import com.nab.icommerce.purchaseorderservice.repository.PurchaseOrderDetailRepository
import com.nab.icommerce.purchaseorderservice.utils.PurchaseOrderStatus
import com.nab.icommerce.purchaseorderservice.utils.TestUtils
import spock.lang.Specification

class PurchaseOrderDetailServiceTest extends Specification {

    private PurchaseOrderDetailService purchaseOrderDetailService
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository = Mock()
    private ObjectMapper objectMapper = new ObjectMapper()
    def setup() {
        purchaseOrderDetailService = new PurchaseOrderDetailService(purchaseOrderDetailRepository)
    }

    def "find Order Details by Order Id"() {
        given:
        List<PurchaseOrderDetail> purchaseOrderDetails = objectMapper
                .readValue(TestUtils.toString("json/purchase-order-detail-list.json"), new TypeReference<List<PurchaseOrderDetail>>(){})
        purchaseOrderDetailRepository.findByPurchaseOrderId(_) >> purchaseOrderDetails
        when:

        List<PurchaseOrderDetail> result = purchaseOrderDetailService.findByPurchaseOrderId(1L)
        then:
        result != null
    }

    def "create a order detail success"() {
        given:
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .shipAddress("HCM City")
                .totalAmount(new BigDecimal(20000))
                .orderCode("BOK001")
                .status(PurchaseOrderStatus.AVAILABLE.toString())
                .build()
        purchaseOrderDetailRepository.save(_) >> PurchaseOrderDetail.builder().id(5l).build()
        when:
        PurchaseOrderDetail orderDetail = purchaseOrderDetailService.createAOrderDetail(purchaseOrder, 1l, new BigDecimal(5000))
        then:
        orderDetail != null
        orderDetail.getId() == 5L
    }
}
