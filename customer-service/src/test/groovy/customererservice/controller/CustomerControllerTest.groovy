package customererservice.controller

import customererservice.config.IntegrationSpecification
import customererservice.utils.TestUtils
import io.restassured.RestAssured

class CustomerControllerTest extends IntegrationSpecification {
    def getRequest() {
        return RestAssured.given()
    }

    def "test register customer"() {
        given:
        def request = getRequest().body(TestUtils.toString("json/regis-customer.json"))
        when:
        def response = request.post("/v1/purchase-order")
        then:
        response.then().log().all().statusCode(200)
    }

    def "test get customer info"() {
        given:
        def request = getRequest()
        when:
        def response = request.get("/v1/customer?email=test@gmail.com")
        then:
        response.then().log().all().statusCode(200)
    }
}
