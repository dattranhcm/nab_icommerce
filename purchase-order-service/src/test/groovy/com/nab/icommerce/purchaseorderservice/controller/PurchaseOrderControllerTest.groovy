package com.nab.icommerce.purchaseorderservice.controller

import com.nab.icommerce.purchaseorderservice.config.IntegrationSpecification
import com.nab.icommerce.purchaseorderservice.utils.TestUtils
import io.restassured.RestAssured

class PurchaseOrderControllerTest extends IntegrationSpecification {

    def getRequest() {
        return RestAssured.given()
    }
    def "fetch purchase order"() {
        given:
        def request = getRequest()
        when:
        def response = request.get("/v1/purchase-order?customerId=1")
        then:
        response.then().log().all().statusCode(200)
    }

    def "create an purchase order base on shopping cart list"() {
        given:
        def request = getRequest().body(TestUtils.toString("json/create-order.json"))
        when:
        def response = request.post("/v1/purchase-order")
        then:
        response.then().log().all().statusCode(200)
    }

    def "update order status to ON_DELIVERY"() {
        given:
        def request = getRequest().body(TestUtils.toString("json/update-order-status.json"))
        when:
        def response = request.put("/v1/purchase-order")
        then:
        response.then().log().all().statusCode(200)
    }
}
