//package com.vpd.gateway.config.filter;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
//        http
//                .authorizeExchange(authorizeRequests ->
//                        authorizeRequests
//                                .pathMatchers(
//                                        "/login",
//                                        "/login/oauth2/**"
//
//                                ).permitAll()
//                                .anyExchange().authenticated()
//                )
//                .oauth2Client(withDefaults())
//                .oauth2Login(
//                        oAuth2LoginSpec -> {
//                            oAuth2LoginSpec.authorizationRequestResolver(
//
//                            )
//                        }
//                )
//                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec.authenticationEntryPoint(
//                        customAuthenticationEntryPoint()
//                ));
//        return http.build();
//    }
//
//    @Bean
//    public ServerAuthenticationEntryPoint customAuthenticationEntryPoint() {
//        return new RedirectServerAuthenticationEntryPoint("/login/oauth2/code/gateway");
//    }
//
////    @Bean
////    public ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(ReactiveClientRegistrationRepository clientRegistrationRepository) {
////        return new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
////    }
//}
