import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class GatewayApplicationTest extends Specification {
    @Autowired
    ApplicationContext ctx;
    def "context loads" () {}
}
