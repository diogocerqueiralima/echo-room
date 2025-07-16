package com.github.diogocerqueiralima.authorizationserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.List;


public record CreateClientDto(
        @NotBlank(message = "client id should not be blank") String clientId,
        @NotBlank(message = "client name should not be blank") String clientName,
        @NotEmpty(message = "you should define at least one redirect uri") List<String> redirectUris,
        @NotEmpty(message = "you should define at least one scope") List<String> scopes,
        @NotEmpty(message = "you should define at least one authorization grant type") List<AuthorizationGrantType> authorizationGrantTypes
) {}
