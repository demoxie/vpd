package com.vpd.gateway.config.filter;

import com.vpd.gateway.exception.APIException;
import com.vpd.gateway.response.APIResponse;
import com.vpd.gateway.response.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.time.Duration;
import java.util.Objects;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public CustomFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            if(validator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw APIException.builder()
                            .statusCode(401)
                            .message("Authorization header is missing")
                            .build();
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).stream().findFirst().orElse("");

                String[] parts = authHeader.split(" ");

                if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                    throw APIException.builder()
                            .statusCode(401)
                            .message("Authorization header is invalid")
                            .build();
                }

                 return webClientBuilder.build()
                        .post()
                        .uri("/auth/validateToken?token=" + parts[1])
                        .retrieve().bodyToMono(APIResponse.class)
                         .flatMap(apiResponse -> {
                             log.info("CUSTOMER: {}", apiResponse);
                             CustomerVO customer = apiResponse.getData();
                             log.info("CUSTOMER: {}", customer);
                             ServerWebExchange modifiedExchange = exchange.mutate()
                                     .request(r -> r.header("X-auth-user-id", String.valueOf(customer.getId())))
                                     .build();
                             return chain.filter(modifiedExchange);
                         });
//            }
        };
    }

    public static class Config {
        // empty class as I don't need any particular configuration
    }
}