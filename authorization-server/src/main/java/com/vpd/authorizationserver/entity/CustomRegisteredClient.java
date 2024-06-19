//package com.vpd.authorizationserver.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//
//import java.util.Set;
//
//@EqualsAndHashCode(callSuper = true)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//public class CustomRegisteredClient extends RegisteredClient {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String id;
//    private String clientId;
//    private String clientSecret;
//    @ElementCollection
//    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
//    @ElementCollection
//    private Set<AuthorizationGrantType> authorizationGrantTypes;
//    @ElementCollection
//    private Set<String> redirectUris;
//    @ElementCollection
//    private Set<String> scopes;
//}
