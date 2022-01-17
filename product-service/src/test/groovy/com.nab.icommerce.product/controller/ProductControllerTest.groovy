package com.nab.icommerce.product.controller

import com.nab.icommerce.product.config.IntegrationSpecification
import com.nab.icommerce.product.utils.TestUtils
import io.restassured.RestAssured

class ProductControllerTest extends IntegrationSpecification {

    def getRequest() {
        return RestAssured.given()
    }

    def "test create a new product item"() {
        given:
        def request = getRequest().body(TestUtils.toString("json/new-product.json"))
        when:
        def response = request.post("/v1/product")
        then:
        response.then().log().all().statusCode(200)
    }

    def "test update info a product"() {
        given:
        def request = getRequest().body(TestUtils.toString("json/update-product.json"))
        when:
        def response = request.put("/v1/product")
        then:
        response.then().log().all().statusCode(200)
    }

    def "fetch info a product by product id"() {
        given:
        def request = getRequest()
        when:
        def response = request.get("/v1/product?productCode=BOK001")
        then:
        response.then().log().all().statusCode(200)
    }
}
