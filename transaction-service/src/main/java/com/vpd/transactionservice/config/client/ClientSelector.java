package com.vpd.transactionservice.config.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClientSelector {
    @Qualifier("authorizationServerWebClient")
    private final WebClient authorizationServerWebClient;
    @Qualifier("accountServiceWebClient")
    private final WebClient accountServiceWebClient;

    public ClientSelector(WebClient authorizationServerWebClient, WebClient accountServiceWebClient) {
        this.authorizationServerWebClient = authorizationServerWebClient;
        this.accountServiceWebClient = accountServiceWebClient;
    }


    public WebClient select(String serviceName) {
        return switch (serviceName) {
            case "auth" -> authorizationServerWebClient;
            case "account" -> accountServiceWebClient;
            default -> null;
        };
    }
}
