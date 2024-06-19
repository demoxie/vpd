//package com.vpd.authorizationserver.config.security;
//
//import com.vpd.authorizationserver.entity.CustomRegisteredClient;
//import com.vpd.authorizationserver.repository.CustomRegisteredClientRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
////@Import(OAuth2AuthorizationServerConfiguration.class)
//public class RegisteredClientConfig {
//
//    private final CustomRegisteredClientRepository registeredClientRepository;
//
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        return new RegisteredClientRepository() {
//            @Override
//            public void save(RegisteredClient registeredClient) {
//                CustomRegisteredClient customRegisteredClient = CustomRegisteredClient.builder()
//                        .id(registeredClient.getId())
//                        .clientId(registeredClient.getClientId())
//                        .clientSecret(registeredClient.getClientSecret())
//                        .clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods())
//                        .authorizationGrantTypes(registeredClient.getAuthorizationGrantTypes())
//                        .redirectUris(registeredClient.getRedirectUris())
//                        .scopes(registeredClient.getScopes())
//                        .build();
//                log.info("Registering client {}", registeredClient);
//                registeredClientRepository.save(customRegisteredClient);
//            }
//
//            @Override
//            public RegisteredClient findById(String id) {
//                return registeredClientRepository.findById(Long.valueOf(id))
//                        .map(this::mapToRegisteredClient)
//                        .orElse(null);
//            }
//
//            @Override
//            public RegisteredClient findByClientId(String clientId) {
//                return registeredClientRepository.findByClientId(clientId)
//                        .map(this::mapToRegisteredClient)
//                        .orElse(null);
//            }
//
//            private RegisteredClient mapToRegisteredClient(CustomRegisteredClient customRegisteredClient) {
//                return RegisteredClient.withId(customRegisteredClient.getId())
//                        .clientId(customRegisteredClient.getClientId())
//                        .clientSecret(customRegisteredClient.getClientSecret())
//                        .clientAuthenticationMethods(clientAuthenticationMethods-> customRegisteredClient.setClientAuthenticationMethods(customRegisteredClient.getClientAuthenticationMethods()))
//                        .authorizationGrantTypes(authorizationGrantTypes-> customRegisteredClient.setAuthorizationGrantTypes(customRegisteredClient.getAuthorizationGrantTypes()))
//                        .redirectUris(redirectUris-> customRegisteredClient.setRedirectUris(customRegisteredClient.getRedirectUris()))
//                        .scopes(scopes-> customRegisteredClient.setScopes(customRegisteredClient.getScopes()))
//                        .build();
//            }
//        };
//    }
//}
