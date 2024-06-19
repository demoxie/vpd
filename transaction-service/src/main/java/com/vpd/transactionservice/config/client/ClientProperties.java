package com.vpd.transactionservice.config.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "client")
public class ClientProperties {
    private AccountService accountService;
    private AuthorizationServer authorizationServer;
    private Integer timeout;
}
