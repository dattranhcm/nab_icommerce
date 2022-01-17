package com.nab.icommerce.product.service

import com.nab.icommerce.productservice.entity.Product
import com.nab.icommerce.productservice.model.ProductRequest
import com.nab.icommerce.productservice.model.ProductResponse
import com.nab.icommerce.productservice.repository.ProductRepository
import com.nab.icommerce.productservice.service.ProductService
import com.nab.icommerce.productservice.utils.ProductStatus
import spock.lang.Specification

class ProductServiceTest extends Specification {
    private ProductService productService
    private ProductRepository productRepository = Mock()
    def setup() {
        productService = new ProductService(productRepository)
    }

    def "test create a product"() {
        given:
        ProductRequest request = ProductRequest.builder()
                .productName("SGK1")
        .status(ProductStatus.AVAILABLE.toString())
        .price(new BigDecimal(20000))
        .productCode("BOK001")
        .build()

        productRepository.save(_) >> Product.builder()
                .productName("SGK1")
                .productCode("BOK001")
                .price(new BigDecimal(20000))
                .status(ProductStatus.AVAILABLE.toString())
                .build()
        when:
        ProductResponse result = productService.addNewProduct(request)
        then:
        result != null
        result.getData() != null
        result.getData().get(0).getProductName() == "SGK1"
        result.getData().get(0).getProductCode() == "BOK001"
    }

    def "test get product item"() {
        given:
        productRepository.findByProductNameOrProductCodeOrStatus(_,_,_) >> List.of(Product.builder()
                .productName("SGK_1")
                .productCode("BOK001")
                .build()
        )
        when:
        ProductResponse result = productService.findByProductNameOrProductCode("SGK_1", "BOK001", null)
        then:
        result != null
        result.getData() != null
        result.getData().get(0).getProductCode() == "BOK001"
        result.getData().get(0).getProductName() == "SGK_1"
    }


}
