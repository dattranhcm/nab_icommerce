package com.nab.icommerce.purchaseorderservice.feign;

import com.nab.icommerce.purchaseorderservice.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.util.annotation.Nullable;

@FeignClient("customer-service")
public interface CustomerClient {
    @GetMapping(value = "/icommerce/v1/customer")
    @ResponseBody
    public CustomerResponse getCustomer(@Nullable @RequestParam(value = "id") Long id,
                                        @Nullable @RequestParam(value = "email") String email,
                                        @Nullable @RequestParam(value = "mobile") String mobile);
}
