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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ProductClient productClient;
    private CustomerClient customerClient;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductClient productClient, CustomerClient customerClient) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productClient = productClient;
        this.customerClient = customerClient;
    }

    @SneakyThrows
    public ShoppingCartResponse getListItemFormShoppingCartByCustomerId(Long customerId) {
        List<ShoppingCart> shoppingCartEntities = shoppingCartRepository.findByCustomerId(customerId);
        if(shoppingCartEntities.isEmpty()) {
            return ShoppingCartResponse.builder().build();
        }
        List<ShoppingCartResponse.ShoppingCartItem> productItems = new ArrayList<>();
        shoppingCartEntities.forEach(shoppingCart -> {
            ProductResponse productResponse = productClient.getProducts(null, null, null, shoppingCart.getProductId());
            productItems.add(ShoppingCartResponse.ShoppingCartItem.builder()
            .id(shoppingCart.getId())
                    .productPrice(shoppingCart.getProductPrice())
                    .productName(Objects.isNull(productResponse) ? null : productResponse.getData().get(0).getProductName())
                    .status(shoppingCart.getStatus())
                    .createdTime(shoppingCart.getCreatedTime())
                    .updatedTime(shoppingCart.getUpdatedTime())
            .build()
            );
        });
        CustomerResponse customerResponse = customerClient.getCustomer(customerId, null, null);
        ShoppingCartResponse response = ShoppingCartResponse.builder()
                .customerId(customerId)
                .data(productItems)
                .build();

        if(Objects.nonNull(customerResponse)
                && Objects.nonNull(customerResponse.getData())) {
            response.setCustomerInfo(
                    ShoppingCartResponse.CustomerInfo.builder()
                            .firstName(customerResponse.getData().get(0).getFirstName())
                            .lastName(customerResponse.getData().get(0).getLastName())
                            .address(customerResponse.getData().get(0).getAddress())
                            .mobile(customerResponse.getData().get(0).getMobile()).build()
            );
        }
        return response;
    }

    @SneakyThrows
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
