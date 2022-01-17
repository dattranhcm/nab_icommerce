package com.nab.icommerce.shoppingcartservice.service;

import com.nab.icommerce.shoppingcartservice.entity.ShoppingCart;
import com.nab.icommerce.shoppingcartservice.feign.CustomerClient;
import com.nab.icommerce.shoppingcartservice.feign.ProductClient;
import com.nab.icommerce.shoppingcartservice.model.CustomerResponse;
import com.nab.icommerce.shoppingcartservice.model.ProductResponse;
import com.nab.icommerce.shoppingcartservice.model.ShoppingCartRequest;
import com.nab.icommerce.shoppingcartservice.model.ShoppingCartResponse;
import com.nab.icommerce.shoppingcartservice.repository.ShoppingCartRepository;
import com.nab.icommerce.shoppingcartservice.utils.ShoppingCartStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CustomerClient customerClient;


    public ShoppingCartResponse getListItemFormShoppingCartByCustomerId(Long customerId) {
        List<ShoppingCart> shoppingCartEntities = shoppingCartRepository.findByCustomerId(customerId);
        List<ShoppingCartResponse.ShoppingCartItem> productItems = new ArrayList<>();
        shoppingCartEntities.forEach(shoppingCart -> {
            ProductResponse productResponse = productClient.getProducts(null, null, null, shoppingCart.getProductId());
            productItems.add(ShoppingCartResponse.ShoppingCartItem.builder()
            .id(shoppingCart.getId())
                    .productPrice(shoppingCart.getProductPrice())
                    .productName(productResponse.getData().get(0).getProductName())
                    .status(shoppingCart.getStatus())
                    .createdTime(shoppingCart.getCreatedTime())
                    .updatedTime(shoppingCart.getUpdatedTime())
            .build()
            );
        });
        CustomerResponse customerResponse = customerClient.getCustomer(customerId, null, null);
        return ShoppingCartResponse.builder().customerInfo(
                ShoppingCartResponse.CustomerInfo.builder()
                        .firstName(customerResponse.getData().get(0).getFirstName())
                        .lastName(customerResponse.getData().get(0).getLastName())
                        .address(customerResponse.getData().get(0).getAddress())
                        .mobile(customerResponse.getData().get(0).getMobile())
                        .build()
        ).data(productItems).build();
    }

    public ShoppingCartResponse addNewItemToShoppingCart(ShoppingCartRequest request) {
        ShoppingCart shoppingCart = shoppingCartRepository.save(ShoppingCart.builder()
                .productId(request.getProductId())
                .productPrice(request.getPrice())
                .customerId(request.getCustomerId())
                .status(ShoppingCartStatus.AVAILABLE.toString())
                .createdTime(new Date())
                .updatedTime(new Date())
                .build());
        return ShoppingCartResponse.builder()
                .data(List.of(ShoppingCartResponse.ShoppingCartItem.builder()
                        .productPrice(shoppingCart.getProductPrice())
                        .status(shoppingCart.getStatus())
                        .build()
                ))
                .build();
    }
}
