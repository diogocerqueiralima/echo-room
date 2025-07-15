package com.github.diogocerqueiralima.authorizationserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record ClientDto(
        @NotNull UUID id, @NotBlank String clientId, @NotBlank String clientName,
        @NotEmpty Set<String> scopes, @NotEmpty Set<String> authorizationGrantTypes
) {}
