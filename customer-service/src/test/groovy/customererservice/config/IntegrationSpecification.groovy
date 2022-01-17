package customererservice.config

import com.github.tomakehurst.wiremock.WireMockServer
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationSpecification extends Specification {
    private WireMockServer mockServer;

    @LocalServerPort
    int port;

    @Value('${server.servlet.context-path}')
    String contextPath

    def setup() {
        mockServer = new WireMockServer(9561)
        mockServer.start()
        RestAssured.requestSpecification = new RequestSpecBuilder()
        .setPort(port)
        .setBasePath(contextPath)
        .setContentType(ContentType.JSON)
        .build()
    }

    def cleanup() {
        mockServer.stop()
    }
}
