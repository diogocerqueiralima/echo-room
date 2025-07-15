package com.github.diogocerqueiralima.authorizationserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public record CreateClientDto(
        @NotBlank String clientId, @NotBlank String clientName, @NotBlank String clientSecret,
        @NotEmpty String[] redirectUris, @NotEmpty String[] scopes
) {}
