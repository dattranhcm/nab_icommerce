package shoppingcartservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nab.icommerce.shoppingcartservice.entity.ShoppingCart
import com.nab.icommerce.shoppingcartservice.model.ShoppingCartResponse
import com.nab.icommerce.shoppingcartservice.repository.ShoppingCartRepository
import com.nab.icommerce.shoppingcartservice.service.ShoppingCartService
import shoppingcartservice.utils.TestUtils
import spock.lang.Specification

class ShoppingCartServiceTest extends Specification {
    private ShoppingCartService shoppingCartService
    private ShoppingCartRepository shoppingCartRepository = Mock()
    private ObjectMapper objectMapper = new ObjectMapper()
    def setup() {
        shoppingCartService = new ShoppingCartService(shoppingCartRepository)
    }

    def "test add item into shopping cart of customer"() {
        given:
        shoppingCartRepository.save(_) >> objectMapper
                .readValue(TestUtils.toString("json/purchase-order-detail-list.json"), new TypeReference<ShoppingCart>(){})
        when:
        ShoppingCartResponse result = shoppingCartService.addNewItemToShoppingCart(_)
        then:
        result != null
        result.getData() != null
        result.getData().get(0).getCustomerId() == 1L
        result.getData().get(0).productId == 1L
        result.getData().get(0).getProductPrice() == 20000
    }

    def "test get list item from customer shopping cart"() {
        given:
        shoppingCartRepository.findByCustomerId(_) >> List.of(ShoppingCart.builder()
                .productPrice(new BigDecimal(20000))
                .productId(1L)
                .build()
        )
        when:
        ShoppingCartResponse result = shoppingCartService.getListItemFormShoppingCartByCustomerId(1L)

        then:
        result != null
        result.getData() != null
        result.getData().get(0).getProductPrice() == 20000
        result.getData().get(0).getProductId() == 1L
    }

}
