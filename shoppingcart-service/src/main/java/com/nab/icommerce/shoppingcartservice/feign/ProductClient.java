package com.nab.icommerce.shoppingcartservice.feign;

import com.nab.icommerce.shoppingcartservice.model.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/icommerce/v1/product")
    @ResponseBody
    public ProductResponse getProducts(@Nullable @RequestParam(value = "productName") String productName,
                                       @Nullable @RequestParam(value = "productCode") String productCode,
                                       @Nullable @RequestParam(value = "status") String status,
                                       @Nullable @RequestParam(value = "id") Long id);
}
