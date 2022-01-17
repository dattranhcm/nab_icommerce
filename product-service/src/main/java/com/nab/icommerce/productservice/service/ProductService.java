package com.nab.icommerce.productservice.service;

import com.nab.icommerce.productservice.entity.Product;
import com.nab.icommerce.productservice.model.ProductRequest;
import com.nab.icommerce.productservice.model.ProductResponse;
import com.nab.icommerce.productservice.repository.ProductRepository;
import com.nab.icommerce.productservice.utils.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse findByProductNameOrProductCode(String productName, String productCode, String status, Long productId) {
        log.info(String.format("%s, %s, %s, %s", productName, productCode, status, productId));
        if(Objects.nonNull(productId)) {
            return findByProductId(productId);
        }

        List<Product> productEntities = productRepository.findByProductNameOrProductCodeOrStatus(productName, productCode, status);
        List<ProductResponse.ProductItem> productItems = new ArrayList<>();
        productEntities.forEach(product -> {
            productItems.add(ProductResponse.ProductItem.builder()
            .productCode(product.getProductCode())
            .productName(product.getProductName())
            .status(product.getStatus())
            .price(product.getPrice())
            .createdTime(product.getCreatedTime())
            .updatedTime(product.getUpdatedTime())
            .id(product.getId())
            .build()
            );
        });
        return ProductResponse.builder().data(productItems).build();
    }

    public ProductResponse findByProductId(Long productId) {
        Optional<Product> productEntities = productRepository.findById(productId);
        Product product = productEntities.get();
        return ProductResponse.builder().data(List.of(ProductResponse.ProductItem.builder()
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .status(product.getStatus())
                        .price(product.getPrice())
                        .createdTime(product.getCreatedTime())
                        .updatedTime(product.getUpdatedTime())
                        .id(product.getId())
                        .build())
        ).build();
    }

    public ProductResponse addNewProduct(ProductRequest request) {
        Product product = productRepository.save(Product.builder()
                .productName(request.getProductName())
                .productCode(request.getProductCode())
                .status(ProductStatus.AVAILABLE.toString())
                .price(request.getPrice())
                .createdTime(new Date())
                .updatedTime(new Date())
                .build());

        return ProductResponse.builder()
                .data(List.of(ProductResponse.ProductItem.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .price(product.getPrice())
                .status(product.getStatus())
                .build()))
                .build();
    }
}
