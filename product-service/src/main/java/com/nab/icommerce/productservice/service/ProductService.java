package com.nab.icommerce.productservice.service;

import com.nab.icommerce.productservice.entity.Product;
import com.nab.icommerce.productservice.model.ProductRequest;
import com.nab.icommerce.productservice.model.ProductResponse;
import com.nab.icommerce.productservice.repository.ProductRepository;
import com.nab.icommerce.productservice.utils.ProductStatus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SneakyThrows
    public ProductResponse findByProductNameOrProductCodeOrStatusOrId(String productName, String productCode, String status, Long productId) {
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
            .metadata(product.getMetadata())
            .price(product.getPrice())
            .id(product.getId())
            .build()
            );
        });
        return ProductResponse.builder().data(productItems).build();
    }

    @SneakyThrows
    public ProductResponse findByProductId(Long productId) {
        log.info(String.format("%s", productId));
        Optional<Product> productEntities = productRepository.findById(productId);
        if(!productEntities.isPresent()) {
            return ProductResponse.builder().build();
        }
        Product product = productEntities.get();
        return ProductResponse.builder().data(List.of(ProductResponse.ProductItem.builder()
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .status(product.getStatus())
                        .metadata(product.getMetadata())
                        .price(product.getPrice())
                        .id(product.getId())
                        .build())
        ).build();
    }

    @SneakyThrows
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
                .metadata(product.getMetadata())
                .build()))
                .build();
    }
}
