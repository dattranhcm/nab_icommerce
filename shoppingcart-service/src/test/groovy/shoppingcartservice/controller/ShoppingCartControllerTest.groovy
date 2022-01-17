package shoppingcartservice.controller

import io.restassured.RestAssured
import shoppingcartservice.config.IntegrationSpecification
import shoppingcartservice.utils.TestUtils

class ShoppingCartControllerTest extends IntegrationSpecification {

    def getRequest() {
        return RestAssured.given()
    }

    def "test add item into shopping cart of customer"() {
        given:
        def request = getRequest().body(TestUtils.toString("json/add-item-shopping-cart.json"))
        when:
        def response = request.post("/v1/shopping-cart")
        then:
        response.then().log().all().statusCode(200)
    }

    def "test get list item from customer shopping cart"() {
        given:
        def request = getRequest()
        when:
        def response = request.get("/v1/shopping-cart?customerId=1")
        then:
        response.then().log().all().statusCode(200)
    }
}
