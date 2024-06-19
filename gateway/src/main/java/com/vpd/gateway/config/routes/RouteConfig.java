package com.vpd.gateway.config.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RouteConfig {
    private static final String[] whiteListedRoutes = {
            "/actuator/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui/index.html",
            "/swagger-ui/**",
            "/auth/login",
            "/auth/logout",
            "/auth/register",
            "/auth/forgot-password",
            "/auth/reset-password"
    };

//    public RouteLocator getRouteLocator() {
//        return Arrays.stream(whiteListedRoutes).map(r -> new RouteLocator(r))
//    }
}
