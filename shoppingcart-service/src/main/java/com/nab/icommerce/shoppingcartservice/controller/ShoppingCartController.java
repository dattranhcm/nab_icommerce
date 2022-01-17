package com.nab.icommerce.shoppingcartservice.controller;

import com.nab.icommerce.shoppingcartservice.model.ShoppingCartRequest;
import com.nab.icommerce.shoppingcartservice.model.ShoppingCartResponse;
import com.nab.icommerce.shoppingcartservice.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ShoppingCartResponse addItemIntoShoppingCart(@RequestBody ShoppingCartRequest request) {
        return shoppingCartService.addNewItemToShoppingCart(request);
    }

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ShoppingCartResponse updateShoppingCart(@RequestBody ShoppingCartRequest request) {
        return ShoppingCartResponse.builder().build();
    }

    @GetMapping(value = "")
    @ResponseBody
    public ShoppingCartResponse getListItemFormShoppingCartByCustomerId(@RequestParam(value = "customerId") Long customerId) {
        return shoppingCartService.getListItemFormShoppingCartByCustomerId(customerId);
    }

}
