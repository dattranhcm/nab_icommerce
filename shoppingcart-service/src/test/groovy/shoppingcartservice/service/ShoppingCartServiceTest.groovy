package shoppingcartservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nab.icommerce.shoppingcartservice.entity.ShoppingCart
import com.nab.icommerce.shoppingcartservice.feign.CustomerClient
import com.nab.icommerce.shoppingcartservice.feign.ProductClient
import com.nab.icommerce.shoppingcartservice.model.CustomerResponse
import com.nab.icommerce.shoppingcartservice.model.ProductResponse
import com.nab.icommerce.shoppingcartservice.model.ShoppingCartRequest
import com.nab.icommerce.shoppingcartservice.model.ShoppingCartResponse
import com.nab.icommerce.shoppingcartservice.repository.ShoppingCartRepository
import com.nab.icommerce.shoppingcartservice.service.ShoppingCartService
import shoppingcartservice.utils.TestUtils
import spock.lang.Specification

class ShoppingCartServiceTest extends Specification {
    private ShoppingCartService shoppingCartService
    private ShoppingCartRepository shoppingCartRepository = Mock()
    private ProductClient productClient = Mock()
    private CustomerClient customerClient = Mock()
    private ObjectMapper objectMapper = new ObjectMapper()
    def setup() {
        shoppingCartService = new ShoppingCartService(shoppingCartRepository, productClient, customerClient)
    }

    def "test add item into shopping cart of customer"() {
        given:
        shoppingCartRepository.save(_) >> objectMapper
                .readValue(TestUtils.toString("json/add-item-shopping-cart.json"), new TypeReference<ShoppingCart>(){})
        when:
        ShoppingCartResponse result = shoppingCartService.addNewItemToShoppingCart(new ShoppingCartRequest())
        then:
        result != null
        result.getData() != null
        result.getData().get(0).getProductPrice() == 20000
    }

    def "test get list item from customer shopping cart"() {
        given:
        List<ShoppingCart> shoppingCarts = new ArrayList<>()
        shoppingCarts.add(ShoppingCart.builder()
                .productPrice(new BigDecimal(20000))
                .productId(1L)
                .build())
        shoppingCartRepository.findByCustomerId(_) >> shoppingCarts

        List<ProductResponse.ProductItem> data = new ArrayList<>()
        data.add(ProductResponse.ProductItem.builder()
                .id(1L)
                .status("AVAILABLE")
                .productCode("BOK01")
                .productName("Clean Code")
                .createdTime(new Date())
                .updatedTime(new Date())
                .price(new BigDecimal(20000))
                .build())
        productClient.getProducts(_,_,_,_) >> ProductResponse.builder()
                .data(data)
                .currentPage(1)
                .totalItems(1)
                .build()

        List<CustomerResponse.CustomerItem> customerItems = new ArrayList<>()
        customerItems.add(CustomerResponse.CustomerItem.builder()
                .address("HCM")
                .firstName("Test")
                .lastName("Customer")
                .build())
        customerClient.getCustomer(_, _, _) >> CustomerResponse.builder()
                .data(customerItems)
                .totalItems(1)
                .currentPage(1)
                .build()

        when:
        ShoppingCartResponse result = shoppingCartService.getListItemFormShoppingCartByCustomerId(1L)

        then:
        result != null
        result.getData() != null
        result.getData().get(0).getProductPrice() == 20000
    }
}
