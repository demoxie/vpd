//package com.vpd.accountservice.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//        security.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
//            authorizationManagerRequestMatcherRegistry.requestMatchers("/actuator/**").permitAll()
//                    .anyRequest().authenticated();
//                })
//                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
//                    httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults());
//                });
//        return security.build();
//    }
//}
