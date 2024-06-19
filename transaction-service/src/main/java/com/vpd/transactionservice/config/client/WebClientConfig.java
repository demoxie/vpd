package com.vpd.transactionservice.config.client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final ClientProperties clientProperties;


    @Bean(name = "accountServiceWebClient")
    public WebClient accountServiceWebClient() {

        return WebClient
                .builder()
                .baseUrl(clientProperties.getAccountService().getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .defaultHeaders(httpHeaders -> httpHeaders
                        .setAll(
                                Map.of(
                                        "X-API-KEY", clientProperties.getAccountService().getClientId(),
                                        "X-API-SECRET", clientProperties.getAccountService().getClientSecret(),
                                        "Content-Type", MediaType.APPLICATION_JSON_VALUE,
                                        "Accept", MediaType.APPLICATION_JSON_VALUE
                                )
                        )
                )
                .build();
    }

    @Bean(name = "authorizationServerWebClient")
    public WebClient authorizationServerWebClient(){
        return WebClient.builder()
                .baseUrl(clientProperties.getAuthorizationServer().getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .defaultHeaders(
                        httpHeaders -> httpHeaders
                                .setAll(
                                        Map.of(
                                                "X-API-KEY", clientProperties.getAuthorizationServer().getClientId(),
                                                "X-API-SECRET", clientProperties.getAuthorizationServer().getClientSecret(),
                                                "Content-Type", MediaType.APPLICATION_JSON_VALUE,
                                                "Accept", MediaType.APPLICATION_JSON_VALUE
                                        )
                                )
                )
                .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, clientProperties.getTimeout())
                .responseTimeout(Duration.ofMillis(clientProperties.getTimeout()))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(clientProperties.getTimeout(), TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(clientProperties.getTimeout(), TimeUnit.MILLISECONDS)));
    }
}
