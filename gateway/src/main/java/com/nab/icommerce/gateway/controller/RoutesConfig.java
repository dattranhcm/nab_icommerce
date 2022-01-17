package com.nab.icommerce.gateway.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("routes")
@Getter
@Setter
public class RoutesConfig {
    private Map<String, String> uri;
}
