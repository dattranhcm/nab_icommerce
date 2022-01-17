package com.nab.icommerce.gateway.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {
    private static Map<String,String> alwaysFail = Collections.singletonMap("status", "FAIL");
    private final WebClient webClient;
    private final RoutesConfig routesConfig;

    public HealthCheckController(WebClient.Builder weBuilder, RoutesConfig routesConfig) {
        this.webClient = weBuilder.build();
        this.routesConfig = routesConfig;
    }

    public Map<String, Map<String, String>> health() {
        Map<String, Map<String, String>> status = new HashMap<>();
        routesConfig.getUri().forEach((route, url) -> status.put(route, getStatus(url)));
        return status;
    }

    private Map<String, String> getStatus(String url) {
        try {
            return webClient
                    .get()
                    .uri(url + "/health")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
                    })
                    .block();
        } catch (Exception ex){
            return alwaysFail;
        }
    }
}
