package com.vpd.transactionservice.config.client;

import lombok.Data;

@Data
public class AuthorizationServer {
    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private String[] scopes;
}
