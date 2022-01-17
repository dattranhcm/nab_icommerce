package com.nab.icommerce.productservice.controller;

import com.nab.icommerce.productservice.model.ProductRequest;
import com.nab.icommerce.productservice.model.ProductResponse;
import com.nab.icommerce.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return productService.addNewProduct(request);
    }

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProductResponse updateProduct(@RequestBody ProductRequest request) {
        return ProductResponse.builder().build();
    }

    @GetMapping(value = "")
    @ResponseBody
    public ProductResponse getProducts(@Nullable @RequestParam(value = "productName") String productName,
                                       @Nullable @RequestParam(value = "productCode") String productCode,
                                       @Nullable @RequestParam(value = "status") String status,
                                       @Nullable @RequestParam(value = "id") Long id) {
        return productService.findByProductNameOrProductCode(productName, productCode, status, id);
    }

}
