package com.github.diogocerqueiralima.authorizationserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ClientDto(
        @JsonProperty("client_id") String clientId,
        @JsonProperty("redirect_uris") String[] redirectUris,
        @JsonProperty("client_id_issued_at")Instant clientIdIssuedAt
) {}
