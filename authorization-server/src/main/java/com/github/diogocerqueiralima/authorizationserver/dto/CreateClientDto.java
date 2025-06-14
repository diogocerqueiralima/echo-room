package com.github.diogocerqueiralima.authorizationserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public record CreateClientDto(
        @JsonProperty("client_id") @NotBlank String clientId,
        @JsonProperty("client_secret") @NotBlank String clientSecret,
        @JsonProperty("redirect_uris") @NotEmpty String[] redirectUris,
        @NotEmpty String[] scopes
) {}
